package dg.nisum.users.user.domain;

import java.util.Date;

public class CreatedDateMother {
    public static CreatedDate create(Date date){
        return new CreatedDate(date);
    }

    public static CreatedDate random(){
        return  create(new Date());
    }
}
