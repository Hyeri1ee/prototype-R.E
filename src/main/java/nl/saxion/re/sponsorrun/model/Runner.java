package nl.saxion.re.sponsorrun.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Runner {
    public  String name;
    public  String event;
    public  String email;
    public int index;
}
