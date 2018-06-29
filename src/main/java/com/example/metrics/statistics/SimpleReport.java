package com.example.metrics.statistics;

/**
 * Implementation of the StatisticReport
 *
 * @author Miguel.Mendez
 */
final class SimpleReport implements StatisticReport {
    private final String message;
    private final double value;

    private SimpleReport(String message, double value){
        this.message = message;
        this.value = value;
    }

    static SimpleReport of(String message, double value) {
        if("".equals(message) || null == message){
            return new SimpleReport("No message was provided", value);
        }

        return new SimpleReport(message, value);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getValue() {
        return this.value;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleReport that = (SimpleReport) o;

        if (Double.compare(that.value, value) != 0) return false;
        return message != null ? message.equals(that.message) : that.message == null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = message != null ? message.hashCode() : 0;
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
