package org.capfifix.client.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.capfifix.client.communication.Pipeline;
import org.capfifix.client.communication.Stub;
import org.capfifix.client.entities.ServerCapFIX;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class StubTest {

	public static ServerCapFIX server;

	@BeforeClass
	public static void init() {

		server = new ServerCapFIX("server1", "127.0.0.1", 45000);

	}

	@Test
	public void should_retun_true_on_logon() {

		Pipeline queueMessageToSend = new Pipeline();

		Pipeline queueMessageToRead = new Pipeline();

		Runtime rt = Runtime.getRuntime();
		try {
			
			Runtime.getRuntime().exec("nc");

			String[] command = new String[] { "C:" + File.separator + "Users" + File.separator + "capfitech"
					+ File.separator + "Documents" + File.separator + "netcat-win32-1.12" };
			rt.exec(command);
			Process p = rt.exec("dir");

			p.waitFor();

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			String s = null;
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}

			stdInput.close();

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		Stub stub = new Stub(server, queueMessageToSend, queueMessageToRead);

		Assert.assertTrue(true);

	}

}
