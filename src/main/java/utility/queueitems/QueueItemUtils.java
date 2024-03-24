package utility.queueitems;

import com.sun.mail.util.QEncoderStream;
import org.testng.annotations.Test;
import utility.Constants;
import utility.Log;
import utility.SQLServerConnection;
import utility.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Queue;


public class QueueItemUtils {
    String insertTransactionsQuery;
    int rowsAffected;

    QueueItemPOJO queueItemFields;
    LocalDateTime localDateTime;

    String workitemId;
    LocalDate localDate;
    String detail;

    Connection connection;



    public QueueItemUtils(){

    }


    public static void main(String args[]) throws SQLException {
        QueueItemUtils queueItemUtils=new QueueItemUtils();
        queueItemUtils.addQueueItem();
        queueItemUtils.getQueueItem();
        queueItemUtils.setQueueStatus("Successful");
    }

    public void addQueueItem() throws SQLException {

        queueItemFields = new QueueItemPOJO();
        connection=SQLServerConnection.connectToSQLDb();
        PreparedStatement statement;

        insertTransactionsQuery = Constants.INSERT_INTODB_QUERY;

        localDateTime = LocalDateTime.now();
        workitemId = localDateTime.toString();
        localDate = LocalDate.now();
        detail = localDate.toString();
        queueItemFields.setWork_item_id(workitemId);
        queueItemFields.setQueue_name(Constants.QUEUE_NAME);
        queueItemFields.setState("Performer");
        queueItemFields.setStatus("New");
        queueItemFields.setDetail(detail);

        try {
            statement = connection.prepareStatement(insertTransactionsQuery);

            //statement.setInt(1,queueItemFields.getId());
            statement.setString(1, queueItemFields.getWork_item_id());
            statement.setString(2, queueItemFields.getQueue_name());
            statement.setString(3, queueItemFields.getState());
            statement.setString(4, queueItemFields.getStatus());
            statement.setString(5, queueItemFields.getDetail());
            rowsAffected = statement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            if (rowsAffected == 1) {
                Log.info("Transaction added to queue with work_item_id: " + workitemId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

    }

    public void getQueueItem() throws SQLException {
        queueItemFields = new QueueItemPOJO();
        connection=SQLServerConnection.connectToSQLDb();
        PreparedStatement statement2;

        Log.info("Getting Transaction item");
        queueItemFields.setStatus("InProgress");

        try {
            String UpdateStatusQuery = Constants.UPDATEQUERY_GETQUEUEITEM;
            System.out.println(UpdateStatusQuery);
            statement2 = connection.prepareStatement(UpdateStatusQuery);
            statement2.setString(1, queueItemFields.getStatus());
            statement2.setString(2, "New");
            statement2.setString(3, workitemId);
            System.out.println(statement2);
            rowsAffected = statement2.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            if (rowsAffected == 1) {
                Log.info("Transaction set to InProgress for work_item_id: " + workitemId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {


        }

    }


    public void setQueueStatus(String status) {
        queueItemFields = new QueueItemPOJO();
        connection=SQLServerConnection.connectToSQLDb();
        Log.info("Setting queue status");
        //status="Successful";
        queueItemFields.setStatus(status);
        PreparedStatement statement3;
        try {
            String UpdateStatusQuery = Constants.UPDATEQUERY_GETQUEUEITEM;
            System.out.println(UpdateStatusQuery);
            statement3 = connection.prepareStatement(UpdateStatusQuery);
            statement3.setString(1, queueItemFields.getStatus());
            statement3.setString(2, "InProgress");
            statement3.setString(3, workitemId);
            rowsAffected = statement3.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            if (rowsAffected == 1) {
                Log.info("Transaction set to "+status+" for work_item_id: " + workitemId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {


        }
    }
}


