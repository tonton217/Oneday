package tonton217.io.oneday.realm;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.realm.Realm;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author wjjung
 * @see <a href="https://github.com/realm/realm-java/tree/master/examples" > Realm Examples </a>
 */

@RunWith(AndroidJUnit4.class)
public class RealmTests {

    private Realm realm;

    @Before
    public void setUp() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Realm.init(appContext);
        realm = Realm.getDefaultInstance();
    }

    private void assertPerson(Person person1, Person person2) {
        assertThat(person1.getId(), is(person2.getId()));
        assertThat(person1.getName(), is(person2.getName()));
        assertThat(person1.getAge(), is(person2.getAge()));
    }

    @Test
    public void crud() throws Exception {
        Person tony = new Person(1, "Anthony", 12);
        Person kris = new Person(2, "Kris", 15);
        // create
        realm.executeTransaction(
                realm -> {
                    Person person = realm.createObject(Person.class);
                    person.setId(person.getId());
                    person.setName(person.getName());
                    person.setAge(person.getAge());
                });

        final Person got = realm.where(Person.class).findFirst();
        assertPerson(tony, got);

        realm.executeTransaction(
                realm -> {
                    got.setId(kris.getId());
                    got.setName(kris.getName());
                    got.setAge(kris.getAge());
                }
        );

        final Person got2 = realm.where(Person.class).findFirst();
        assertPerson(kris, got2);

        realm.executeTransaction(
                realm -> realm.delete(Person.class)
        );
    }

    @After
    public void tearDown() throws Exception {
        if (realm != null)
            realm.close();
    }
}
