package cn.zephyr.ch16.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName MySession
 * @Author laizonghao
 * @CreateTime 2023/2/2 23:26
 * @Description
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MySession {
    private String userId;
    private String username;
    private int age;
}
