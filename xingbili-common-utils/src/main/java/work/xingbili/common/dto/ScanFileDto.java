 package work.xingbili.common.dto;

 import io.swagger.annotations.ApiModel;
 import io.swagger.annotations.ApiModelProperty;
 import lombok.AllArgsConstructor;
 import lombok.Data;
 import lombok.EqualsAndHashCode;
 import lombok.NoArgsConstructor;

 import java.io.Serializable;

 /**
  *  文件DTO
  *
  * @author TGB
  * @date 2021/06/21
  */
  @ApiModel(description = "文件DTO")
  @SuppressWarnings("serial")
  @Data
  @EqualsAndHashCode(callSuper = false)
  @AllArgsConstructor
  @NoArgsConstructor
  public class ScanFileDto implements Serializable{

      /**
       * 文件id
       */
      @ApiModelProperty(value = "文件id")
      private Long fileId;

      /**
       * 文件名称
       */
      @ApiModelProperty(value = "文件名称")
      private String fileName;
  }
