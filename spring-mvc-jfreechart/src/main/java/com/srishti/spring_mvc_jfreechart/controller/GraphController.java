package com.srishti.spring_mvc_jfreechart.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.jfree.chart.*;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;

@Controller
public class GraphController {

    @RequestMapping("index")
    public String sayHelloJsp(ModelMap model) {
        DefaultCategoryDataset barChartDataset = new DefaultCategoryDataset();
        barChartDataset.addValue(200, "Sales", "January");
        barChartDataset.addValue(150, "Sales", "February");
        barChartDataset.addValue(180, "Sales", "March");
        barChartDataset.addValue(260, "Sales", "April");
        barChartDataset.addValue(300, "Sales", "May");


        BufferedImage  barChartImg = ChartFactory.createBarChart(
                "Monthly Sales",
                "Month",
                "Sales",
                barChartDataset).createBufferedImage(400, 400);



        model.addAttribute("barChartImg", createImage(barChartImg));



        DefaultPieDataset<String> pieChartDataset = new DefaultPieDataset<>();
        pieChartDataset.setValue("January", 200);
        pieChartDataset.setValue("February", 150);
        pieChartDataset.setValue("March", 180);

        BufferedImage  pieChartImg  = ChartFactory.createPieChart(
                "Monthly Sales",
                pieChartDataset,
                true,
                true,
                false).createBufferedImage(400,400);

        model.addAttribute("pieChartImg", createImage(pieChartImg));
        return "index";
    }


    private String createImage(BufferedImage buffImg){
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        try {
            ImageIO.write(buffImg, "png", bas);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] imageBytes=bas.toByteArray();

        return Base64.getEncoder().encodeToString(imageBytes);
    }
 }
