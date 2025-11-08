package com.example.omtechie.SpringJPAProj.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="PRODUCT_TBL")
@EntityListeners(AuditingEntityListener.class)
@Audited
public class Product {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
	private String name;
	private double price;
	private String description;
	private String productType;
	@CreatedBy
	private String createdBy;
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP) // DATE TIME or BOTH(TIMESTAMP)
	private Date createdDate;

	@LastModifiedBy
	private String lastModifiedBy;
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	public Product(String demo, int i, String demoProduct, String sampleProduct) {
	}
}
