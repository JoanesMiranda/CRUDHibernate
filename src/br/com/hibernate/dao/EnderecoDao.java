/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernate.dao;

import br.com.hibernate.util.HibernateUtil;
import br.com.hibernate.modelo.Endereco;
import br.com.hibernate.modelo.Pessoa;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Suporte
 */
public class EnderecoDao {

    private Session sessao;
    private Transaction trans;

    public void salvarEndereco(Endereco end) {
        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();

            sessao.save(end);
            trans.commit();

        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        } finally {
            sessao.close();
        }
    }

    public List<Endereco> pesquisaEndereco(Endereco end) {

        sessao = HibernateUtil.getSessionFactory().openSession();
        trans = sessao.beginTransaction();
        Query q = sessao.createQuery("from Endereco WHERE pessoa_id = :id");
        q.setParameter("id", end.getId());
        trans.commit();
        List list = q.list();
        return list;
    }

    public void atualizarEndereco(Endereco end) {

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            Query query = sessao.createQuery("UPDATE Endereco SET rua = :rua, numero = :num  WHERE pessoa_id = :id ");

            query.setParameter("rua", end.getRua());
            query.setParameter("num", end.getNum());
            query.setParameter("id", end.getId());

            query.executeUpdate();
            trans.commit();

        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        } finally {
            sessao.close();
        }
    }
}
