package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by User on 10/14/2016.
 */
public class ContactPhoneEmailTest extends TestBase {

  @Test
  public void testContactPhones(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));


  }

  private String mergePhones(ContactData contact) {
  return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()).stream()
          .filter((s) -> s != null && !s.equals("")).map(ContactPhoneEmailTest::cleaned).collect(Collectors.joining("\n"));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3()).stream()
            .filter((s) -> s != null && !s.equals("")).map(ContactPhoneEmailTest::cleaned).collect(Collectors.joining("\n"));
  }

  public static String cleaned (String phone){
    return phone.replaceAll("\\s", "").replaceAll("[-()]","");
  }

}
