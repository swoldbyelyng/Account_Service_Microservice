package user_service.rabbitMQ;

public class ConfigureRabbitMQ {

    public static final String QUEUE_NAME = "loggerQueue";
    public static final String EXCHANGE_NAME = "loggerExchange";
    public static final String ROUTING_KEY = "logger.#";
}
