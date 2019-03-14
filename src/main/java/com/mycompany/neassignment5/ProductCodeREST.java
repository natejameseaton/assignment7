/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.neassignment5;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author c0711874
 */

// http://localhost:8080/NEAssignment5/api/productCode

@Path("productCode")
@ApplicationScoped
public class ProductCodeREST {
    
    @PersistenceContext(unitName = "neassignment5PU")
    private EntityManager em;
    
    @GET
    @Produces({"application/json"})
    public List<ProductCode> getAll() {
        List<ProductCode> productCodes = em.createQuery("SELECT p FROM ProductCode p").getResultList();
        return productCodes;
    }
    
    @GET
    @Path("id")
    @Produces({"application/json"})
    public List<ProductCode> getOne(@PathParam("id") String id) {
        Query q = em.createQuery("SELECT p FROM ProductCode p WHERE p.prodCode = :id");
        q.setParameter("id", id);
        List<ProductCode> productCodes = q.getResultList();
        return productCodes;
    }
    
    @Inject
    private UserTransaction transaction;
    
    @POST
    @Consumes("application/json")
    public void addOne(ProductCode productCode) {
        try {
            transaction.begin();
            em.persist(productCode);
            transaction.commit();     
        } catch (Exception ex) {
            Logger.getLogger(ProductCodeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @PUT
    @Consumes("application/json")
    public void editOne(ProductCode productCode, @PathParam("id") String id) {
        try {
            transaction.begin();
            em.persist(productCode);
            transaction.commit();     
        } catch (Exception ex) {
            Logger.getLogger(ProductCodeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
