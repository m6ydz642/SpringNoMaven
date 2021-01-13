package project.rasp.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import project.rasp.model.User;



public interface UserMapper {

  public User getUserById(int id);
  public int add(User user);
  public int update(User user);
  public User getInformation();
  
  @Delete("delete from users where id = #{id}")
  public int delete(@Param("id") int id);

}
