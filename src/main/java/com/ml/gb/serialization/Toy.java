package com.ml.gb.serialization;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;

public class Toy {
	public static void main(String[] args) throws IOException {
		File schemaFile = new File("src/main/avro/user.avsc");
		Schema schema = new Schema.Parser().parse(schemaFile);
		GenericRecord user1 = new GenericData.Record(schema);
		user1.put("name", "mlgb");
		user1.put("id", 12345l);
		GenericRecord user2 = new GenericData.Record(schema);
		user2.put("name", "bglm");
		user2.put("id", 54321l);
		// Serialize into bytes[]
		File dstFile = new File("src/main/avro/user.avro");
		DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(
				schema);
		OutputStream out = new ByteArrayOutputStream();
		Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);
		datumWriter.write(user1, encoder);
		datumWriter.write(user2, encoder);
		encoder.flush();
		out.close();

		// DataFileWriter<GenericRecord> dataFileWriter = new
		// DataFileWriter<GenericRecord>(
		// datumWriter);
		// dataFileWriter.create(schema, dstFile);
		// dataFileWriter.append(user1);
		// dataFileWriter.append(user2);
		// dataFileWriter.close();
		// Deserialize
		DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(
				schema);
		BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(
				((ByteArrayOutputStream) out).toByteArray(), null);
		GenericRecord user = null;

		while ((user = datumReader.read(null, decoder)) != null) {
			System.out.println(user);
		}
		// DataFileReader<GenericRecord> dataFileReader = new
		// DataFileReader<GenericRecord>(
		// dstFile, datumReader);
		// GenericRecord user = null;
		// while (dataFileReader.hasNext()) {
		// user = dataFileReader.next(user);
		// System.out.println(user);
		// System.out.println(user.get("name"));
		// System.out.println(user.get("id"));
		// }
	}
}
