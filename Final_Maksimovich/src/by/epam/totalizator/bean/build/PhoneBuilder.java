package by.epam.totalizator.bean.build;

import by.epam.totalizator.bean.Phone;

/**
 * 
 * Realization of builder pattern for Phone bean
 * {@link Phone}
 *
 */
public class PhoneBuilder {

	private String code;
	private String phoneNumber;

	public PhoneBuilder buildCode(String code) {
		this.code = code;
		return this;
	}

	public PhoneBuilder buildPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	public Phone build() {
		Phone phone = new Phone();

		phone.setCode(code);
		phone.setPhoneNumber(phoneNumber);

		return phone;
	}
}
