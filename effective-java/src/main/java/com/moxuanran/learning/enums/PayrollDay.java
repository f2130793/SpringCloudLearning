package com.moxuanran.learning.enums;

/**
 * @author wutao
 * @date 2022/10/12 14:44
 */
public enum PayrollDay {
    /**
     * 周一
     */
    MONDAY(),
    SUNDAY(PayType.WEEKEND)
    ;

    private final PayType payType;

    PayrollDay(PayType payType) {
        this.payType = payType;
    }

    PayrollDay() {
        this(PayType.WEEKDAY);
    }

    int pay(int minutesWorked, int payRate) {
        return payType.pay(minutesWorked, payRate);
    }

    /**
     * 支付类型
     *
     * @author wutao
     * @date 2022/10/12
     */
    private enum PayType {
        /**
         * 工作日
         */
        WEEKDAY {
            @Override
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked <= MINS_PER_SHIFT ? 0 : (minsWorked - MINS_PER_SHIFT) * payRate / 2;
            }
        },
        /**
         * 周末
         */
        WEEKEND {
            @Override
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked * payRate / 2;
            }
        };

        abstract int overtimePay(int mins, int payRate);

        private static final int MINS_PER_SHIFT = 8 * 60;

        int pay(int minsWorked, int payRate) {
            int basePay = minsWorked * payRate;
            return basePay + overtimePay(minsWorked, payRate);
        }
    }
}
