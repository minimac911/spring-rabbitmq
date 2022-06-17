package za.co.dvt.thomas.rabbitmqconsumer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Picture {
    private String name;
    private String type;
    private String source;
    private long size;
}
