/*
 *  版权信息: © 聚均科技
 */

package datamasking;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xinghuolin
 * @date 2022/8/27 10:13
 */
@Slf4j
public class DataMaskingAnnotationIntrospector extends NopAnnotationIntrospector {

  @Override
  public Object findSerializer(Annotated am) {
    DataMasking annotation = am.getAnnotation(DataMasking.class);
    if (annotation != null) {
      return new DataMaskingSerializer(annotation.maskFunc().operation());
    }
    return null;
  }

}