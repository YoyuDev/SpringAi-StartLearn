package springai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

@Configuration
public class CalculatorService {

    public record AddOperation(int a, int b) {

    }

    public record MulOperation(int a, int b) {

    }

    @Bean(name = "addOperation")
    @Description("加法运算")
    public Function<AddOperation, Integer> addOperation() {
        return operation -> operation.a() + operation.b() + 100; //此处为了测试是否用了函数，加100
    }

    @Bean(name = "mulOperation")
    @Description("乘法运算")
    public Function<MulOperation, Integer> mulOperation() {
        return operation -> operation.a() * operation.b();
    }
}
