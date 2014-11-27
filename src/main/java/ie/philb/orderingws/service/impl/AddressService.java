/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.service.impl;

import ie.philb.orderingws.dao.AddressDao;
import ie.philb.orderingws.dao.CountryDao;
import ie.philb.orderingws.dao.DaoException;
import ie.philb.orderingws.dao.NoSuchEntityDaoException;
import ie.philb.orderingws.model.Address;
import ie.philb.orderingws.service.IAddressService;
import ie.philb.orderingws.service.ServiceException;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Stateless
@WebService
public class AddressService implements IAddressService {

    private static final Logger logger = Logger.getLogger(AddressService.class.getSimpleName());
    private final AddressDao addressDao;

    public AddressService() throws ServiceException {
        try {
            logger.info("Getting initial context");
            InitialContext cxt = new InitialContext();

            logger.info("Getting datasource");
            DataSource ds = (DataSource) cxt.lookup("jdbc/ordering");

            if (ds == null) {
                throw new ServiceException("Failed to initialise AddressService, DataSource not found");
            }
            
            logger.info("Creating dao");
            addressDao = new AddressDao(ds);
        } catch (NamingException nx) {
            throw new ServiceException("Failed to initialise AddressService", nx);
        }
    }

    @Override
    public List<Address> list() throws ServiceException {
        try {
            return addressDao.list();
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Address get(Long id) throws ServiceException {
        try {
            return addressDao.get(id);
        } catch (DaoException | NoSuchEntityDaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public int delete(Long id) throws ServiceException {
        try {
            return addressDao.delete(id);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public long create(Address country) throws ServiceException {
        try {
            return addressDao.create(country);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public int update(Address country) throws ServiceException {
        try {
            return addressDao.update(country);
        } catch (DaoException | NoSuchEntityDaoException ex) {
            throw new ServiceException(ex);
        }
    }

}
