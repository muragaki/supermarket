package com.example.supermarket.domain.model;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザー
 * エンティティクラス
 * 
 * @author Muragaki
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usr")
public class User {
	@Id
	private String userId;			// ユーザーID
	private String password;		// パスワード
	private String firstName;		// 氏名　姓
	private String lastName;		// 氏名　名
	@Enumerated(EnumType.STRING)	// DB登録は文字列型
	private RoleName roleName;		// USER/ADMIN
}