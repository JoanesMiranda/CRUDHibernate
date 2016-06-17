/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernate.dao;

import br.com.hibernate.util.HibernateUtil;
import br.com.hibernate.modelo.Pessoa;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Suporte
 */
public class PessoaDao {

    private Session sessao;
    private Transaction trans;

    public void salvarPessoa(Pessoa p) {

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();

            sessao.save(p);
            trans.commit();

        } catch (Exception e) {
            trans.rollback();
            JOptionPane.showMessageDialog(null,"erro ao salvar dados - Pessoa");
        } finally {
            sessao.close();
        }
    }

    public void apagarPessoa(Pessoa pessoa) {

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            Query query = sessao.createQuery("DELETE Pessoa WHERE nome = :nome ");
            query.setParameter("nome", pessoa.getNome());
            query.executeUpdate();
            trans.commit();
            JOptionPane.showMessageDialog(null,"Dados apagados com Sucesso");
        } catch (Exception e) {
            trans.rollback();
            JOptionPane.showMessageDialog(null,"erro ao apagar dados - Pessoa");
        } finally {
            sessao.close();
        }
    }

    public List<Pessoa> pesquisaPessoa(Pessoa pessoa) {

        sessao = HibernateUtil.getSessionFactory().openSession();
        trans = sessao.beginTransaction();
        Query q = sessao.createQuery("from Pessoa WHERE nome = :nome");
        q.setParameter("nome", pessoa.getNome());
        trans.commit();
        q.getFirstResult();
        List list = q.list();
        return list;
    }

    public void atualizarPessoa(Pessoa pessoa) {

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            Query query = sessao.createQuery("UPDATE Pessoa SET email = :e, telefone = :tel  WHERE nome = :nome ");
            query.setParameter("e", pessoa.getEmail());
            query.setParameter("tel", pessoa.getTelefone());
            query.setParameter("nome", pessoa.getNome());

            query.executeUpdate();
            trans.commit();

        } catch (Exception e) {
            trans.rollback();
            JOptionPane.showMessageDialog(null,"erro ao atualizar dados - Pessoa");
        } finally {
            sessao.close();
        }
    }

}
