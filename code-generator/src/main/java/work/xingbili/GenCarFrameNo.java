/*
 *  版权信息: © 聚均科技
 */

package work.xingbili;

import java.util.Random;

/**
 * @author xinghuolin
 * @date 2023/2/8 17:32
 */
public class GenCarFrameNo {

  /**
   * 车架号地区代码数组
   */

  public static final String areaArray[] = new String[]{"1", "2", "3", "6", "9", "J", "K", "L", "R",
      "S", "T", "V", "W", "Y", "Z", "G"};

  /**
   * 车架号中可能出现的字符数组
   */

  public static final String charArray[] = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "A",
      "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "R", "S", "T", "V", "W", "X",
      "Y"};

  /**
   * 车架号校验位计算数组
   */

  public static final Object[][] KVMACTHUP = new Object[][]{{'A', 1}, {'B', 2}, {'C', 3}, {'D', 4},
      {'E', 5}, {'F', 6}, {'G', 7}, {'H', 8}, {'I', 0}, {'J', 1}, {'K', 2}, {'L', 3}, {'M', 4},
      {'N', 5}, {'O', 0}, {'P', 7}, {'Q', 8}, {'R', 9}, {'S', 2}, {'T', 3}, {'U', 4}, {'V', 5},
      {'W', 6}, {'X', 7}, {'Y', 8}, {'Z', 9}};

  /**
   * 车架号数据加权数组
   */

  public static final int[] WEIGHTVALUE = new int[]{8, 7, 6, 5, 4, 3, 2, 10, 0, 9, 8, 7, 6, 5, 4, 3,
      2};

  // 根据车架号校验方法，计算合法校验位的值。

  /**
   * 计算车架号的校验位
   *
   * @return
   */

  public String getIsuredCode(String vin) {

    char[] Vin = vin.toCharArray();

    int sum = 0, tempValue = 0;

    char temp;

    for (int i = 0; i < 17; i++) {

      if (Vin[i] >= 'a' && Vin[i] <= 'z') {

        temp = (char) (Vin[i] - 32);

      } else if ((Vin[i] >= 'A') && (Vin[i] <= 'Z')) {

        temp = Vin[i];

      } else if ((Vin[i] >= '0') && (Vin[i] <= '9')) {

        tempValue = Integer.parseInt(String.valueOf(Vin[i]));

        temp = Vin[i];

      } else {

        return "ERROR";

      }

      if ((temp >= 'A') && (temp <= 'Z')) {

        for (int j = 0; j < 26; j++) {

          if (temp == (char) KVMACTHUP[j][0]) {

            tempValue = (int) KVMACTHUP[j][1];

          }

        }

      }

      sum += tempValue * WEIGHTVALUE[i];

    }

    int reslt = sum % 11;

    if (reslt != 10) {

      return String.valueOf(reslt);

    } else {

      return "X";

    }

  }

  //判断计算出的校验位，是否与车架号校验位相同。

  /**
   * 判断vin是否正确
   *
   * @param vin
   * @return
   */

  public boolean isVin(String vin) {

    String isuredCode = getIsuredCode(vin);

    if (vin.substring(8, 9).equals(isuredCode)) {

      return true;

    } else {

      return false;

    }

  }


  /**
   * 拼接车架号
   *
   * @param beforeStr
   * @param afterStr
   * @return
   */

  public String spellVin(String beforeStr, String afterStr) {

    StringBuffer vinBuffer = new StringBuffer();

    String preVin = vinBuffer.append(beforeStr).append("X").append(afterStr).toString();

    String isuredCode = getIsuredCode(preVin);

    String vin = new StringBuffer(beforeStr).append(isuredCode).append(afterStr).toString();

    if (isVin(vin)) {

      return vin;

    } else {

      return spellVin(beforeStr, afterStr);

    }

  }

  /**
   * 生成随机前缀
   *
   * @return
   */

  public String prepareBeforeStr() {

    StringBuffer stringBuffer = new StringBuffer();

    stringBuffer.append("VNN");

    for (int i = 0; i < 5; i++) {

      stringBuffer.append(getRandomChar(areaArray));

    }

    return stringBuffer.toString();

  }

  /**
   * 生成随机后缀
   *
   * @return
   */

  public String prepareAfterStr() {

    StringBuffer stringBuffer = new StringBuffer();

    for (int i = 0; i < 3; i++) {

      stringBuffer.append(getRandomChar(charArray));

    }

    stringBuffer.append(prepareNo());

    return stringBuffer.toString();

  }

  /**
   * 生成随机的生产序号
   *
   * @return
   */

  public String prepareNo() {

    Random random = new Random();

    StringBuffer numStrBuff = new StringBuffer();

    for (int i = 0; i < 5; i++) {

      numStrBuff.append(Integer.toHexString(random.nextInt(9)).toUpperCase());

    }

    return numStrBuff.toString();

  }

  /**
   * 返回随机字符
   *
   * @return
   */

  public String getRandomChar(Object array[]) {

    return charArray[(int) (Math.random() * 100 % array.length)];

  }


  public static void main(String[] args) {
    GenCarFrameNo genCarFrameNo = new GenCarFrameNo();
    for (int i = 0; i < 1000; i++) {
      System.out.println(genCarFrameNo.spellVin(genCarFrameNo.prepareBeforeStr(),
          genCarFrameNo.prepareAfterStr()));
    }
  }

}