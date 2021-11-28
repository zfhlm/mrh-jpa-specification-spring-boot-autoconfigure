package org.lushen.mrh.boot.data.jpa.specification.test.example;

import java.util.Arrays;

import org.lushen.mrh.boot.data.jpa.specification.example.JpaExample;
import org.lushen.mrh.boot.data.jpa.specification.predicate.JpaAndPredicates;
import org.lushen.mrh.boot.data.jpa.specification.test.service.HelloEntity;

public class TestExample {

	public static void main(String[] args) {

		JpaExample<HelloEntity> example = JpaExample.from(HelloEntity.class);

		JpaAndPredicates<HelloEntity> predicates = example.where();
		predicates.equal(HelloEntity::getId, "123");
		predicates.in(HelloEntity::getGender, Arrays.asList(1, 2, 3));
		predicates.or().equal(HelloEntity::getUsername, "张三").equal(HelloEntity::getUsername, "李四");

		example.desc(HelloEntity::getId);

		System.out.println(example);

	}

}
