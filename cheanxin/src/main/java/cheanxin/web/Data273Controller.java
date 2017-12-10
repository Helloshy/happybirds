package cheanxin.web;

import cheanxin.config.OuterResourceConfig;
import cheanxin.domain.CarBrand;
import cheanxin.domain.CarBrandResponse;
import cheanxin.domain.CarSeries;
import cheanxin.domain.CarType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by 向麒 on 2017/4/19.
 */
@RestController
@RequestMapping("/273data")
public class Data273Controller extends BaseController {
    @Autowired
    private OuterResourceConfig outerResourceConfig;

    @RequestMapping(value="/brand",method = RequestMethod.GET)
    public ResponseEntity<List<CarBrand>> list() {
        CarBrandResponse carBrandResponse = null;
        try {
            URI uri = new URI(outerResourceConfig.carBrandUrl);
            SimpleClientHttpRequestFactory schr = new SimpleClientHttpRequestFactory();
            ClientHttpRequest chr = schr.createRequest(uri, HttpMethod.POST);
            //chr.getBody().write(param.getBytes());//body中设置请求参数
            ClientHttpResponse res = chr.execute();
            InputStream is = res.getBody(); //获得返回数据,注意这里是个流
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String str = "";

            while((str = br.readLine())!=null){
                System.out.println(str);//获得页面内容或返回内容
                ObjectMapper mapper = new ObjectMapper();
                carBrandResponse = mapper.readValue(str, CarBrandResponse.class);
            }

        } catch (URISyntaxException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<>(carBrandResponse.getData(), HttpStatus.OK);
    }

    @RequestMapping(value="/series",method = RequestMethod.GET)
    public ResponseEntity<List<CarSeries>> listSeries(@RequestParam(value = "brandId", defaultValue = "-1") long brandId) {
        CarBrandResponse carBrandResponse = null;
        try {
            URI uri = new URI(outerResourceConfig.serieUrl+brandId);
            SimpleClientHttpRequestFactory schr = new SimpleClientHttpRequestFactory();
            ClientHttpRequest chr = schr.createRequest(uri, HttpMethod.GET);
            //chr.getBody().write(param.getBytes());//body中设置请求参数
            ClientHttpResponse res = chr.execute();
            InputStream is = res.getBody(); //获得返回数据,注意这里是个流
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String str = "";

            while((str = br.readLine())!=null){
                System.out.println(str);//获得页面内容或返回内容
                ObjectMapper mapper = new ObjectMapper();
                carBrandResponse = mapper.readValue(str, CarBrandResponse.class);
            }

        } catch (URISyntaxException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<>(carBrandResponse.getData(), HttpStatus.OK);
    }

    @RequestMapping(value="/types",method = RequestMethod.GET)
    public ResponseEntity<List<CarType>> listType(@RequestParam(value = "seriesId", defaultValue = "-1") long seriesId) {
        CarBrandResponse carBrandResponse = null;
        try {
            URI uri = new URI(outerResourceConfig.typeUrl+seriesId);
            SimpleClientHttpRequestFactory schr = new SimpleClientHttpRequestFactory();
            ClientHttpRequest chr = schr.createRequest(uri, HttpMethod.GET);
            //chr.getBody().write(param.getBytes());//body中设置请求参数
            ClientHttpResponse res = chr.execute();
            InputStream is = res.getBody(); //获得返回数据,注意这里是个流
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String str = "";

            while((str = br.readLine())!=null){
                System.out.println(str);//获得页面内容或返回内容
                ObjectMapper mapper = new ObjectMapper();
                carBrandResponse = mapper.readValue(str, CarBrandResponse.class);
            }

        } catch (URISyntaxException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<>(carBrandResponse.getData(), HttpStatus.OK);
    }
}
