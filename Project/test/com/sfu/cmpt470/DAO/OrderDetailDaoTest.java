package com.sfu.cmpt470.DAO;

import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.database.RowMapper.OrderDetailRowMapper;
import com.sfu.cmpt470.pojo.OrderDetail;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class OrderDetailDaoTest {

    private static final String DISH_NAME = "some fish";
    @Spy
    private
    OrderDetailDAO _dao;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        _dao._db = mock(DatabaseConnector.class);
    }

    @Test
    public void verifyOrderDetail_orderDetailDoesnotExist_shouldReturnError() throws SQLException {
        doThrow(new SQLException("no record found!")).when(_dao._db).queryOneRecord(any(OrderDetailRowMapper.class));

        OrderDetail orderDetail = OrderDetail.newBuilder().setOrderDetailID(-1).build();

        String result = _dao.verifyOrderDetail(orderDetail);

        assert(result.equals("order detail does not exist"));
    }

}
