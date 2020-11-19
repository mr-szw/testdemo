package com.dawei.test.demo.spi;

import java.util.ServiceLoader;

import com.dawei.test.demo.spi.api.SpiInterface;

/**
 * @author sinbad on 2020/11/19.
 */
public class JavaSPI {


	public static void main(String[] args) {


		ServiceLoader<SpiInterface> spiInterfaceServiceLoader = ServiceLoader.load(SpiInterface.class);
	}


}
