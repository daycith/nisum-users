package dg.nisum.users.user.domain;

import java.util.Date;

public class UpdatedDateMother {
    public static UpdatedDate create(Date date){
        return new UpdatedDate(date);
    }

    public static UpdatedDate random(){
        return  create(new Date());
    }
}
