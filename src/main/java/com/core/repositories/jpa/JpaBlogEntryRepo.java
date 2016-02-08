package com.core.repositories.jpa;

import com.core.models.entities.BlogEntry;
import com.core.repositories.BlogEntryRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by fcj on 16/2/7.
 */
@Repository
public class JpaBlogEntryRepo implements BlogEntryRepo {


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public BlogEntry findBlogEntry(Long id) {
        return entityManager.find(BlogEntry.class,id);
    }

    @Override
    public BlogEntry updateBlogEntry(Long id, BlogEntry blogEntry) {
        BlogEntry entry = entityManager.find(BlogEntry.class,id);
        entry.setTitle(blogEntry.getTitle());
        entry.setContent(blogEntry.getContent());
        return entry;
    }

    @Override
    public BlogEntry deleteBlogEntry(Long id) {
        BlogEntry entry = entityManager.find(BlogEntry.class,id);
        entityManager.remove(entry);
        return entry;
    }

    @Override
    public BlogEntry createBlogEntry(BlogEntry data) {
        entityManager.persist(data);
        return data;
    }

    @Override
    public List<BlogEntry> findBlogEntriesByBlogId(Long blogId) {
        Query query = entityManager.createQuery("SELECT b FROM BlogEntry b WHERE b.blog.id=?1");
        query.setParameter(1,blogId);
        return query.getResultList();
    }
}
