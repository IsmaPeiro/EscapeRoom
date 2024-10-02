package dao;

import model.clients.Client;

import java.util.List;

public interface ClientDAO extends DAO<Client, Integer>{
    List<Client> getSubscribedClients() throws DAOException;
    
    void unsubscribeClient(int id) throws DAOException;
    
    void subscribeClient(int id) throws DAOException;
}
