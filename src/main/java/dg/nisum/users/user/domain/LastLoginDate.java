package dg.nisum.users.user.domain;

import dg.nisum.users.shared.domain.DateValueObject;

import java.util.Date;

public class LastLoginDate extends DateValueObject {
    public LastLoginDate(Date value) {
        super(value);
    }
}
