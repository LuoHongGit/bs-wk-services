package com.wk.manage_course.dao;

import com.alibaba.druid.support.spring.stat.SpringStatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wk.framework.domain.course.Teachplan;
import com.wk.framework.domain.course.ext.CategoryNode;
import com.wk.framework.domain.course.ext.CourseInfo;
import com.wk.framework.domain.course.ext.TeachplanNode;
import com.wk.manage_course.ManageCourseApplication;
import com.wk.manage_course.dao.CourseBaseRepository;
import com.wk.manage_course.dao.CourseMapper;
import com.wk.framework.domain.course.CourseBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sound.midi.Soundbank;
import javax.swing.text.TabableView;
import java.util.Optional;

/**
 * @author Administrator
 * @version 1.0
 **/
@SpringBootTest(classes = {ManageCourseApplication.class})
@RunWith(SpringRunner.class)
public class TestDao {
    @Autowired
    CourseBaseRepository courseBaseRepository;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    TeachplanMapper teachplanMapper;
    @Autowired
    CategoryMapper categoryMapper;

    @Test
    public void testCourseBaseRepository1(){

        CourseBase courseBase = new CourseBase();
        courseBase.setId("40280a817637fd6d017637fd8c350000");
        courseBase.setName("测试课程000111");
        courseBase.setMt("1-6");
        courseBase.setSt("1-6-1");
        courseBase.setGrade("200003");
        courseBase.setStudymodel("201001");
        courseBase.setStatus("202001");
        courseBase.setCompanyId("2");

        courseBaseRepository.save(courseBase);


    }

    @Test
    public void testCourseBaseRepository(){
        Optional<CourseBase> optional = courseBaseRepository.findById("402885816240d276016240f7e5000002");
        if(optional.isPresent()){
            CourseBase courseBase = optional.get();
            System.out.println(courseBase);
        }

    }

    @Test
    public void testCourseMapper(){
        CourseBase courseBase = courseMapper.findCourseBaseById("402885816240d276016240f7e5000002");
        System.out.println(courseBase);

    }

    @Test
    public void teachPlanTest(){
        TeachplanNode teachplanNode = teachplanMapper.selectList("4028e581617f945f01617f9dabc40000");
        System.out.println(teachplanNode.getChildren().size());
        System.out.println(teachplanNode);
    }

    @Test
    public void findByPagrTest(){
        PageHelper.startPage(1, 10);

        Page<CourseInfo> courseListPage = courseMapper.findCourseListPage(null);

        System.out.println(courseListPage.getTotal());
        System.out.println(courseListPage.getResult());
    }

    @Test
    public void categoryTest(){
        CategoryNode categoryNode = categoryMapper.findCategoryList();

        System.out.println(categoryNode);
    }
}
