/*
 * Copyright (c) 2017, Robert Bosch (Suzhou) All Rights Reserved. This software is property of
 * Robert Bosch (Suzhou). Unauthorized duplication and disclosure to third parties is prohibited.
 */
package msghandler.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import msghandler.Handler;
import msghandler.HandlerDispatcher;
import msghandler.HandlerDispatcherImpl;
import msghandler.ParentHandler;
import msghandler.annotation.Mapped;
import msghandler.annotation.Parent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * ClassName: HandlerExecutorTest <br>
 * date: May 29, 2018 1:49:38 PM <br>
 *
 * @author dailey.dai@cn.bosch.com DAD2SZH
 * @since JDK 1.8
 */
public class HandlerExecutorCompositeAdvDemo {


    public HandlerExecutorCompositeAdvDemo() {
        handlerDispatcher = new HandlerDispatcherImpl<>();
        handlerDispatcher.load(this);
    }

    private Consumer<String> consumer;


    private HandlerDispatcher<String> handlerDispatcher;//消息分发器

    void init() {
        handlerDispatcher = new HandlerDispatcherImpl<>();
        handlerDispatcher.load(this); //将注解的Handler加载到分发器
    }

    public void onReceivedMessage(String data) {
        //第一个char为消息ID
        handlerDispatcher.getHandler(data.charAt(2) + "").process(data);
    }

    final Supplier<HandlerDispatcher<String>> supplier = () -> handlerDispatcher;

    @Mapped("1") //第一层，处理消息ID为1的消息
    final CustomizedHandler A = (data) -> {
        msghandler.util.Logger.log("A");
    };

    @Mapped("3") //第一层，处理消息ID为1的消息
    final CustomizedHandler C = (data) -> {
        msghandler.util.Logger.log("C");
    };

    @Mapped("2") //第一层，处理消息ID为2的消息
    final ParentHandler<String> B = ParentHandler.build(supplier, (data) -> (data.charAt(1) + ""));

    @Parent("B")
    @Mapped("1") //第二层，处理消息ID为2，子消息ID为1的消息
    final ParentHandler<String> B1 = ParentHandler.build(supplier, (data) -> (data.charAt(2) + ""));

    @Parent("A")
    @Mapped("1") //第二层，处理消息ID为1，子消息ID也为1的消息
    final CustomizedHandler A1 = (data) -> {
        msghandler.util.Logger.log("A1");
    };

    @Parent("B1")
    @Mapped("1") //第三层，处理消息ID为1，子消息ID为2，子子消息ID为1的消息
    final CustomizedHandler B11 = (data) -> {
        msghandler.util.Logger.log("B11");
    };

    @Mapped("1")
    final CustomizedHandler NOT_FOUND = (data) -> {
        //
    };

    @Data
    static
    class Person {
        private String name;
        private int age;
    }

    //public static void main(String[] args) {
    //    Person person = new Person();
    //    person.setAge(1);
    //    person.setName("2323");
    //    List<Person> list = new ArrayList<>();
    //    list.add(person);
    //    person.setName("232");
    //    person.setAge(3);
    //
    //    list.add(person);
    //    System.out.println(list);
    //}

    //public  void main(String[] args) {
    //
    //
    //
    //    System.out.println(list);
    //    List<String> bankCodes = new ArrayList<>(Arrays.asList("11", null));
    //    bankCodes.removeIf((e) -> null == e);
    //    System.out.println(bankCodes);
    //    //HandlerExecutorCompositeAdvDemo d = new HandlerExecutorCompositeAdvDemo();
    //    //d.onReceivedMessage("B111");
    //    //d.onReceivedMessage("2111");
    //}

    public static void main(String[] args) {
        DebtCust d1 = new DebtCust().setDeptId(1L).setCustCode("1").setDebt(new BigDecimal("5.1"));
        DebtCust d2 = new DebtCust().setDeptId(2L).setCustCode("2").setDebt(new BigDecimal("5.1"));
        DebtCust d3 = new DebtCust().setDeptId(1L).setCustCode("1").setDebt(new BigDecimal("5.0"));
        DebtCust d4 = new DebtCust().setDeptId(3L).setCustCode("1").setDebt(new BigDecimal("5.0"));
        DebtCust d5 = new DebtCust().setDeptId(2L).setCustCode("2").setDebt(new BigDecimal("5.1"));
        List<DebtCust> debtCustList = Arrays.asList(new DebtCust[]{d1, d2, d3, d4, d5});
        List<DebtCust> newList = new ArrayList<>();
        debtCustList.parallelStream().collect(Collectors.groupingBy(d -> d.getCustCode(), Collectors.toList()))
                .forEach((custCode, groupList) -> {
                    groupList.stream().reduce(
                            (a, b) -> new DebtCust()
                                    .setCustCode(a.getCustCode())
                                    .setDebt(a.getDebt().add(b.getDebt()))
                    ).ifPresent(newList::add);
                });
        System.out.println(newList);
    }

    public void setConsumer(Consumer<String> consumer) {
        this.consumer = consumer;
    }

    @FunctionalInterface
    interface CustomizedHandler extends Handler<String> {
    }

    void simpleProcess(String data) {
    /*if (consumer != null)
      consumer.accept(data);*/
        msghandler.util.Logger.log(this.getClass().getSimpleName() + " processing.");
    }


}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
class DebtCust{
    private Long deptId;
    private String custCode;
    private BigDecimal debt;
}