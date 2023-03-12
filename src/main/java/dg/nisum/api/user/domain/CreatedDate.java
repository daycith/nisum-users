package dg.nisum.api.user.domain;

import dg.nisum.api.shared.domain.DateValueObject;

import java.util.Date;

public class CreatedDate extends DateValueObject {
    public CreatedDate(Date value) {
        super(value);
    }

    public static CreatedDate current(){
        return new CreatedDate(new Date());
    }
}
