package org.drakosha.tools.calctable.dataprovider.examples.testdatasets.selftest;

import static org.drakosha.tools.calctable.dataprovider.common.util.CalcTableCollectionUtils.listOf;

import java.time.LocalDate;
import java.util.List;

import org.drakosha.tools.calctable.dataprovider.examples.testdatasets.external.model.ContactData;
import org.drakosha.tools.calctable.dataprovider.examples.testdatasets.external.model.Email;
import org.drakosha.tools.calctable.dataprovider.examples.testdatasets.external.model.Person;
import org.drakosha.tools.calctable.dataprovider.examples.testdatasets.external.model.PersonalData;
import org.drakosha.tools.calctable.dataprovider.examples.testdatasets.external.model.Phone;
import org.drakosha.tools.calctable.dataprovider.examples.testdatasets.external.model.PostalAddress;
import org.drakosha.tools.calctable.dataprovider.examples.testdatasets.external.model.type.EEmailKind;
import org.drakosha.tools.calctable.dataprovider.examples.testdatasets.external.model.type.EGender;
import org.drakosha.tools.calctable.dataprovider.examples.testdatasets.external.model.type.EPhoneKind;
import org.drakosha.tools.calctable.dataprovider.examples.testdatasets.external.model.type.EPostalAddressKind;

public interface _TestDataFactory {

	// ... constants

	List<Person> EXPECTED__TEST_DATA_SETS__PERSON_DATA = listOf(
		Person.of(
			PersonalData.of(
				"006-32-9914",
				"David R.",
				"Brown",
				LocalDate.parse("1971-01-13"),
				EGender.MALE
			),
			ContactData.of(
				listOf(
					PostalAddress.of(
						EPostalAddressKind.HOME,
						null,
						"4972 Main Street",
						"Seattle",
						"Washington (WA)",
						98109,
						"US"
					),
					PostalAddress.of(
						EPostalAddressKind.WORK,
						"Sun Shine Co.",
						"222 5th Ave N",
						"Seattle",
						"Washington (WA)",
						98109,
						"US"
					)
				),
				listOf(
					Phone.of(
						EPhoneKind.HOME,
						"+1 425-654-5440"
					),
					Phone.of(
						EPhoneKind.MOBILE,
						"+1 206-356-6647"
					),
					Phone.of(
						EPhoneKind.WORK,
						"+1 206-443-9275"
					)
				),
				listOf(
					Email.of(
						EEmailKind.PRIVATE,
						"david.brown@aol.com"
					),
					Email.of(
						EEmailKind.WORK,
						"david.brown@sun-shine.com"
					)
				)
			)
		),
		Person.of(
			PersonalData.of(
				"610-16-5934",
				"Jennifer J.",
				"Taveras",
				LocalDate.parse("1974-02-09"),
				EGender.FEMALE
			),
			ContactData.of(
				listOf(
					PostalAddress.of(
						EPostalAddressKind.HOME,
						null,
						"6199 S Virginia St",
						"Reno",
						"Nevada (NV)",
						89502,
						"US"
					),
					PostalAddress.of(
						EPostalAddressKind.WORK,
						"Thunder Inc.",
						"2001 E. Plumb Lane",
						"Reno",
						"Nevada (NV)",
						89510,
						"US"
					)
				),
				listOf(
					Phone.of(
						EPhoneKind.HOME,
						"+1 530-986-8096"
					),
					Phone.of(
						EPhoneKind.MOBILE,
						"+1 510-552-0541"
					),
					Phone.of(
						EPhoneKind.WORK,
						"+1 520-365-7412"
					)
				),
				listOf(
					Email.of(
						EEmailKind.PRIVATE,
						"taveras1974@gmail.com"
					),
					Email.of(
						EEmailKind.WORK,
						"j.taveras@thunder.com"
					)
				)
			)
		)
	);

}
