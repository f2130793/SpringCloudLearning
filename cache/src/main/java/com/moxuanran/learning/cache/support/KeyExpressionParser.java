package com.moxuanran.learning.cache.support;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * key解析器
 *
 * @author wutao
 * @date 2020/4/13 13:11
 */
public class KeyExpressionParser {

    private static final ExpressionParser EXPRESSION_PARSER = new SpelExpressionParser();

    public static String parse(String keyExpression, MethodSignature signature, Object[] args) {
        if (StringUtils.isBlank(keyExpression)) {
            return signature.getMethod().getName();
        }
        String[] parameterNames = signature.getParameterNames();
        Expression expression = EXPRESSION_PARSER.parseExpression(keyExpression);
        EvaluationContext evaluationContext = new StandardEvaluationContext();
        for (int i = 0; i < parameterNames.length; i++) {
            evaluationContext.setVariable(parameterNames[i], args[i]);
        }
        return expression.getValue(evaluationContext, String.class);
    }

}
