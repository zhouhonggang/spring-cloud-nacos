package com.zhou.javakc.common.jpa.dynamic.expression;

import com.zhou.javakc.common.jpa.dynamic.criterion.Criterion;

import javax.persistence.criteria.*;

/**
 * 封装公共逻辑表达式查询
 * 复杂查询通过or/and拼接
 * @project javakc
 * @author zhou
 * @date 2019年01月12日下午6:53:13
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
public class JoinExpression implements Criterion {

	/** 当前对象 中 关联对象 的 属性名称 */
	private String fieldName1;
	/** 关联对象 查询 属性名称 */
	private String fieldName2;
	/** 关联对象 查询 属性值 */
	private Object value;
	/** 计算符  */
	private Operator operator;

    public JoinExpression(String fieldName1, String fieldName2, Object value, Operator operator)
    {
        this.fieldName1 = fieldName1;
        this.fieldName2 = fieldName2;
        this.value = value;
        this.operator = operator;
    }

	@Override
	public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    	//排除重复的数据
    	query.distinct(true);
		switch (operator) {
			case eq:
				return builder.equal(root.join(fieldName1, JoinType.LEFT).get(fieldName2), value);
			case ne:
				return builder.notEqual(root.join(fieldName1, JoinType.LEFT).get(fieldName2), value);
			case gt:
				return builder.greaterThan(root.join(fieldName1, JoinType.LEFT).get(fieldName2), (Comparable) value);
			case ge:
				return builder.greaterThanOrEqualTo(root.join(fieldName1, JoinType.LEFT).get(fieldName2), (Comparable) value);
			case lt:
				return builder.lessThan(root.join(fieldName1, JoinType.LEFT).get(fieldName2), (Comparable) value);
			case le:
				return builder.lessThanOrEqualTo(root.join(fieldName1, JoinType.LEFT).get(fieldName2), (Comparable) value);
			case like:
				return builder.like(root.join(fieldName1, JoinType.LEFT).get(fieldName2), "%"+value+"%");
			default:
				return null;
		}
	}

}