package work.xingbili.common.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public  final  class PojoUtils extends BeanUtils {

    private static Logger logger = LogManager.getLogger(PojoUtils.class);

    public PojoUtils() {
    }

    public static Object copy(Object fromPojo, Object toPojo) {
        return copy(fromPojo, toPojo, (List)null, false);
    }

    public static Object copy(Object fromPojo, Object toPojo, List<String> excludeFieldList) {
        return copy(fromPojo, toPojo, excludeFieldList, false);
    }

    public static Object copy(Object fromPojo, Object toPojo, boolean copyNullFlag) {
        return copy(fromPojo, toPojo, (List)null, false);
    }

    public static Object copy(Object fromPojo, Object toPojo, List<String> excludeFieldList, boolean copyNullFlag) {
        if (null != fromPojo && null != toPojo) {
            new HashMap();
            Map<String, Field> fromPojoMap = getClassFields(fromPojo.getClass(), true);
            new HashMap();
            Map<String, Field> toPojoMap = getClassFields(toPojo.getClass(), true);
            Map<String, Method> newMethodMap = new HashMap();
            Map<String, Method> oldMethodMap = new HashMap();
            Method[] methods = fromPojo.getClass().getMethods();
            Method[] var9 = methods;
            int var10 = methods.length;

            int var11;
            Method getMethod;
            for(var11 = 0; var11 < var10; ++var11) {
                getMethod = var9[var11];
                if (getMethod.getName().startsWith("get")) {
                    newMethodMap.put(work.xingbili.common.utils.StringUtils.fstCharLowerCase(getMethod.getName().substring(3)), getMethod);
                }
            }

            methods = toPojo.getClass().getMethods();
            var9 = methods;
            var10 = methods.length;

            for(var11 = 0; var11 < var10; ++var11) {
                getMethod = var9[var11];
                if (getMethod.getName().startsWith("set")) {
                    oldMethodMap.put(work.xingbili.common.utils.StringUtils.fstCharLowerCase(getMethod.getName().substring(3)), getMethod);
                }
            }

            Iterator it = fromPojoMap.keySet().iterator();

            while(true) {
                String newFieldName;
                do {
                    do {
                        do {
                            if (!it.hasNext()) {
                                return toPojo;
                            }

                            newFieldName = (String)it.next();
                        } while(!toPojoMap.containsKey(newFieldName));
                    } while(!((Field)fromPojoMap.get(newFieldName)).getType().getName().equals(((Field)toPojoMap.get(newFieldName)).getType().getName()));
                } while(null != excludeFieldList && (null == excludeFieldList || excludeFieldList.contains(newFieldName)));

                try {
                    Method setMethod = (Method)oldMethodMap.get(newFieldName);
                    getMethod = (Method)newMethodMap.get(newFieldName);
                    if (null != setMethod && null != getMethod) {
                        Object value = getMethod.invoke(fromPojo);
                        if (copyNullFlag || null != value) {
                            setMethod.invoke(toPojo, value);
                        }
                    }
                } catch (IllegalAccessException var14) {
                    logger.error(fromPojo.getClass().getName() + "反射调用出错.");
                } catch (InvocationTargetException var15) {
                    logger.error(fromPojo.getClass().getName() + "反射调用出错.");
                } catch (Exception var16) {
                    logger.error("JAVA POJO对象互相转换出错.");
                }
            }
        } else {
            return null;
        }
    }

    public static Object apacheCopy(Object fromPojo, Object toPojo) {
        try {
            copyProperties(toPojo, fromPojo);
        } catch (IllegalAccessException var3) {
            logger.error(fromPojo.getClass().getName() + "反射调用出错.");
        } catch (InvocationTargetException var4) {
            logger.error(fromPojo.getClass().getName() + "反射调用出错.");
        }

        return toPojo;
    }

    public static List<?> transformListMapToPojo(Class<?> pojoClass, List<Map<String, Object>> dataMapList) {
        List<Object> objectList = new ArrayList();
        Iterator var3 = dataMapList.iterator();

        while(var3.hasNext()) {
            Map dataMap = (Map)var3.next();

            try {
                objectList.add(transformMapToPojo(pojoClass.newInstance(), dataMap));
            } catch (IllegalAccessException var6) {
                logger.error("实例化出错.");
            } catch (InstantiationException var7) {
                logger.error("实例化出错.");
            }
        }

        return objectList;
    }

    public static Object transformMapToPojo(Object toPojo, Map<String, Object> dataMap) {
        if (null != toPojo && null != dataMap && dataMap.size() != 0) {
            new HashMap();
            Map<String, Field> fieldMap = getClassFields(toPojo.getClass(), true);
            Map<String, Method> methodMap = new HashMap();
            Method[] methods = toPojo.getClass().getMethods();
            Method[] var5 = methods;
            int var6 = methods.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                Method method = var5[var7];
                if (method.getName().startsWith("set")) {
                    methodMap.put(work.xingbili.common.utils.StringUtils.fstCharLowerCase(method.getName().substring(3)), method);
                }
            }

            Iterator it = dataMap.keySet().iterator();

            while(it.hasNext()) {
                String key = (String)it.next();
                String field = key.toLowerCase();
                String[] strArray = field.split("_");
                field = "";
                String[] var9 = strArray;
                int var10 = strArray.length;

                for(int var11 = 0; var11 < var10; ++var11) {
                    String str = var9[var11];
                    if (StringUtils.isNotBlank(str)) {
                        field = field + work.xingbili.common.utils.StringUtils.fstCharUpperCase(str);
                    }
                }

                field = work.xingbili.common.utils.StringUtils.fstCharLowerCase(field);
                if (methodMap.containsKey(field) && fieldMap.containsKey(field)) {
                    Method method = (Method)methodMap.get(field);
                    if (null != dataMap.get(key)) {
                        try {
                            method.invoke(toPojo, ConvertUtils.convert(((Field)fieldMap.get(field)).getType(), dataMap.get(key)));
                        } catch (IllegalAccessException var13) {
                            logger.error(method.getName() + "反射调用出错.");
                        } catch (InvocationTargetException var14) {
                            logger.error(method.getName() + "反射调用出错.");
                        }
                    }
                }
            }

            return toPojo;
        } else {
            return null;
        }
    }

    public static Map<String, Field> getClassFields(Class clazz, boolean includeParentClass) {
        Map<String, Field> fieldMap = new HashMap();
        Field[] fields = clazz.getDeclaredFields();
        Field[] var4 = fields;
        int var5 = fields.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Field field = var4[var6];
            fieldMap.put(field.getName(), field);
        }

        if (includeParentClass) {
            getParentClassFields(fieldMap, clazz);
        }

        return fieldMap;
    }

    private static void getParentClassFields(Map<String, Field> fieldMap, Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Field[] var3 = fields;
        int var4 = fields.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Field field = var3[var5];
            fieldMap.put(field.getName(), field);
        }

        if (clazz.getSuperclass() != null) {
            getParentClassFields(fieldMap, clazz.getSuperclass());
        }
    }

    public static boolean hasProperty(Object bean, String fieldName) {
        Map<String, Field> fieldMap = getClassFields(bean.getClass(), true);
        return fieldMap.containsKey(fieldName);
    }

}
