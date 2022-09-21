package com.cts.insurance.claim.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// TODO: Auto-generated Javadoc
/**
 * Gets the balance amt to be paid.
 *
 * @return the balance amt to be paid
 */
@Getter /**
  * Sets the balance amt to be paid.
  *
  * @param balanceAmtToBePaid the new balance amt to be paid
  */
 @Setter /**
  * Instantiates a new initiate claim.
  */
 @NoArgsConstructor /**
  * Instantiates a new initiate claim.
  *
  * @param id the id
  * @param patientName the patient name
  * @param ailment the ailment
  * @param insurerName the insurer name
  * @param balanceAmtToBePaid the balance amt to be paid
  */
 @AllArgsConstructor
@Entity
@Table(name = "initiate_claim")
public class InitiateClaim {
	
	/** The id. */
	@Id
	@GeneratedValue
	private int id;	
	
	/** The patient name. */
	@ApiModelProperty(notes="Patient Name")
	private String patientName;	
	
	/** The ailment. */
	@ApiModelProperty(notes="Ailment")
	private AilmentCategory ailment;
	
	/** The insurer name. */
	@ApiModelProperty(notes="Insurer Name")
	private String insurerName;
	
	/** The balance amt to be paid. */
	@ApiModelProperty(notes="Balance Amount To Be Paid")
	private double balanceAmtToBePaid;
}
