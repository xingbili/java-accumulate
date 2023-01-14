/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.refactor.foursteps;

import lombok.Data;

import java.util.List;
import java.util.Vector;

/**
 * @author xinghuolin
 * @date 2022/7/18 20:00
 */
@Data
public class Customer {
    private List<Play> rentals;
    private String name;

    public Customer(Vector rentals, String name) {
        this.rentals = rentals;
        this.name = name;
    }

    public String state() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental record for" + getName() + "\n";
        for (Play each : rentals) {
            double thisAmount = 0;
            switch (each.getType()) {
                
            }
        }
        return "";
    }


}