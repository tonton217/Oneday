package tonton217.io.oneday.realm;

import io.realm.RealmObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wjjung
 */
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Person extends RealmObject {
    private long id;
    private String name;
    private int age;

    public Person() {}
}
