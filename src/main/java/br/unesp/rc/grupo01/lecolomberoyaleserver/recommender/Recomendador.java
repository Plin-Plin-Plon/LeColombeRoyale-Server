/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.recommender;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Pedido;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

/**
 *
 * @author Christian
 */
public class Recomendador implements RecommenderBuilder {

    @Override
    public Recommender buildRecommender(DataModel model) throws TasteException {
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.2, similarity, model);
        UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

        return recommender;
    }

    public static DataModel getCsvDataModel(String path) throws IOException {
        File file = null;
        try {
            file = new File("src/main/resources/" + path);
            file.createNewFile();
            DataModel data = new FileDataModel(file);
            return data;
        } catch (IOException ex) {
            Logger.getLogger(Recomendador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void writeFormattedCsv(List<Pedido> pedidos, String path) {
        try {
            File file = new File("src/main/resources/" + path);
            file.createNewFile();
            FileWriter outputFile = new FileWriter(file, true);

            CSVWriter writer = new CSVWriter(outputFile, ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END
            );

            for (Pedido pedido : pedidos) {
                String idHospede = String.valueOf(pedido.getHospede().getIdPessoa());
                String idItem = String.valueOf(pedido.getServico().getIdServico());
                String avaliacaoItem = String.valueOf(pedido.getAvaliacaoServico());

                String[] data = {idHospede, idItem, avaliacaoItem};
                writer.writeNext(data);
            }

            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Recomendador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void writeFormattedCsv(Pedido pedido, String path) {
        try {
            File file = new File("src/main/resources/" + path);
            file.createNewFile();

            if (!alreadyEntered(file, pedido)) {
                FileWriter outputFile = new FileWriter(file, true);

                CSVWriter writer = new CSVWriter(outputFile, ',',
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END
                );

                String idHospede = String.valueOf(pedido.getHospede().getIdPessoa());
                String idItem = String.valueOf(pedido.getServico().getIdServico());
                String avaliacaoItem = String.valueOf(pedido.getAvaliacaoServico());

                String[] data = {idHospede, idItem, avaliacaoItem};
                writer.writeNext(data);
                writer.close();
            } else {
                System.out.println("Avaliacao já realizada!");
            }
        } catch (IOException ex) {
            Logger.getLogger(Recomendador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean alreadyEntered(File file, Pedido pedido) throws FileNotFoundException {
        CSVReader reader = new CSVReader(new FileReader(file));
        String[] nextLine;
        try {
            String idPessoa = String.valueOf(pedido.getHospede().getIdPessoa());
            String idServico = String.valueOf(pedido.getServico().getIdServico());
            
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine[0].equals(idPessoa) && nextLine[1].equals(idServico)) {
                    return true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Recomendador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CsvValidationException ex) {
            Logger.getLogger(Recomendador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
