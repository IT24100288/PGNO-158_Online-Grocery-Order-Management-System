package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.service;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.modal.Cart;
import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.modal.Order;
import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.modal.Queue;
import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.utils.Date;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.UUID;

public class OrderService {

    private static final String filePath = "C:\\Users\\USER\\Desktop\\order-management\\data\\order.txt";

    //create new order
    public void createOrder(double totalPrice, ArrayList<Cart> cartItems, String customerEmail, String customerFirstname, String customerLastName, String customerPhone, String customerAddress, String customerCity, String customerPostalCode) {

        String uuid = UUID.randomUUID().toString();
        String orderId = "ORD-"+uuid.substring(0, 8);
        String date = Date.getDate();
        String status = "Pending";

        Order newOrder = new Order(orderId,customerEmail,cartItems,totalPrice,customerFirstname,customerLastName,customerPhone,customerAddress,customerCity,customerPostalCode,date,status);
        saveOrderToFile(newOrder);

    }

    //save order to the file
    public void saveOrderToFile(Order order) {
        try(FileWriter fileWriter = new FileWriter(filePath , true)) {
            BufferedWriter  bw = new BufferedWriter(fileWriter);
            Gson gson = new Gson();
            String orderJson = gson.toJson(order.getProducts());
            bw.write(order.getOrderId()+"//"+order.getCustomerFirstName()+"//" +order.getCustomerLastName()+"//" +order.getCustomerEmail()+"//" +order.getCustomerAddress()+"//" +order.getCustomerCity()+"//" +order.getCustomerPostalCode()+"//" +order.getCustomerPhone()+"//" +order.getTotalPrice()+"//" +order.getOrderDate()+"//" +order.getOrderStatus()+"//" +orderJson);
            bw.newLine();
            bw.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    //get order by id
    public Order getOrderById(String orderId) {

        Gson gson = new Gson();
        Type cartItemType = new TypeToken<ArrayList<Cart>>(){}.getType();

        try(FileReader fileReader = new FileReader(filePath)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("//");
                if(data[0].equals(orderId)){
                    ArrayList<Cart>  cartItems = gson.fromJson(data[11], cartItemType);
                    return new Order(data[0],data[3],cartItems,Double.parseDouble(data[8]),data[1],data[2],data[7],data[4],data[5],data[6],data[9],data[10]);
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    //get all orders by status
    public Queue getAllOrdersQueue() {
        Queue orders = new Queue();
        Gson gson = new Gson();
        Type cartItemType = new TypeToken<ArrayList<Cart>>(){}.getType();

        try(FileReader fileReader = new FileReader(filePath)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("//");
                ArrayList<Cart>  cartItems = gson.fromJson(data[11], cartItemType);
                Order order = new Order(data[0],data[3],cartItems,Double.parseDouble(data[8]),data[1],data[2],data[7],data[4],data[5],data[6],data[9],data[10]);
                orders.insert(order);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return orders;
    }

    //delete order
    public boolean cancelOrder(String orderId, String userId) {

        ArrayList<String> updatedOrder =  new ArrayList<>();
        boolean result = false;

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("//");
                if(data[1].equals(userId) &&  data[0].equals(orderId)){
                    if(data[11].equalsIgnoreCase("Pending")){
                        result = true;
                    }
                }else{
                    updatedOrder.add(line);
                }
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
        }

        if(!result){
            return false;
        }

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for(String order : updatedOrder){
                bufferedWriter.write(order);
                bufferedWriter.newLine();
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean cancelOrder(String orderId) {

        ArrayList<String> updatedOrder =  new ArrayList<>();
        boolean result = false;

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("//");
                if(data[0].equals(orderId)){
                    result = true;

                }else{
                    updatedOrder.add(line);
                }
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
        }

        if(!result){
            return false;
        }

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for(String order : updatedOrder){
                bufferedWriter.write(order);
                bufferedWriter.newLine();
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    //update order status
    public boolean updateOrderStatus(String orderId, String status){
        ArrayList<String> updatedOrders =  new ArrayList<>();
        boolean result = false;

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("//");
                if(data.length == 12){
                    if(data[0].trim().equals(orderId.trim())){
                        data[10] = status;
                        result = true;
                    }
                    updatedOrders.add(String.join("//", data));
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
            return false;
        }

        if(!result){
            return false;
        }

        try(BufferedWriter  bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for(String order : updatedOrders){
                bufferedWriter.write(order);
                bufferedWriter.newLine();
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
