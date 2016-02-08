package com.core.repositories.jpa;

import com.core.models.entities.Blog;
import com.core.repositories.BlogRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by fcj on 16/2/7.
 */
@Repository
public class JpaBlogRepo implements BlogRepo {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Blog createBlog(Blog data) {
        entityManager.persist(data);
        return data;
    }

    @Override
    public List<Blog> findAllBlogs() {
        Query query = entityManager.createQuery("SELECT b FROM Blog b");
        return query.getResultList();
    }

    @Override
    public Blog findBlog(Long id) {

        return entityManager.find(Blog.class,id);
    }

    @Override
    public Blog findBlogByTitle(String title) {
        Query query = entityManager.createQuery("SELECT b FROM Blog b WHERE b.title=?1");
        query.setParameter(1,title);
        List<Blog> blogs = query.getResultList();
        if(blogs.isEmpty()){
            return null;
        }else{
            return blogs.get(0);
        }
    }

    @Override
    public List<Blog> findBlogsByAccount(Long accountId) {
        Query query = entityManager.createQuery("SELECT b from Blog b where b.owner.id=?1");
        query.setParameter(1, accountId);
        return query.getResultList();
    }
}
