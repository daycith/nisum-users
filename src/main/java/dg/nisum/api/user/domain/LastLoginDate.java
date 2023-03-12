package dg.nisum.api.user.domain;

import dg.nisum.api.shared.domain.DateValueObject;

import java.util.Date;

public class LastLoginDate extends DateValueObject {
    public LastLoginDate(Date value) {
        super(value);
    }

    public boolean isEmpty(){
        return this.value() == null;
    }

    public static LastLoginDate current(){
        return new LastLoginDate(new Date());
    }
}
