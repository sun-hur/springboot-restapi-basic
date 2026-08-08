package com.example.demo.model;

/**
 * record 타입: 반복되는 코드 없이 데이터를 저장하는 불변 객체를 쉽게 만드는 기능
 * 
 * 예) 
 * Employee emp = new Employee("222", "홍길동", 25);
 * log.info("{} {} {}", emp.id(), emp.name(), emp.age());
 *
 */
public record Employee(String id, String name, Integer age) {
	
}




//@Entity
//@Table(name="EMPLOYEE")
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class Employee {
//	@Id
//	String id;
//	String name;
//	int age;
//
//}
