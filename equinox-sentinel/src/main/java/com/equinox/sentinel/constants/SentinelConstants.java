package com.equinox.sentinel.constants;

/**
 * @author boreas
 * @create 2022-06-09 13:38
 */
public class SentinelConstants {

    public static class Resource {

        public static final String EXCEPTION_SENTINEL = "exceptionSentinel";

        public static final String ANNOTATION_SENTINEL = "annotationSentinel";

        public static final String DEGRADE_GRADE_RT = "rt";

        public static final String DEGRADE_GRADE_EXCEPTION_RATIO = "ratio";

        public static final String DEGRADE_GRADE_EXCEPTION_COUNT = "count";
    }
}
