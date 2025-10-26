package mst.cli;

import mst.util.DatasetGenerator;

public class Run {
    // Доп. утилита: пример генерации набора данных (запуск отдельной командой)
    public static void main(String[] args) throws Exception {
        DatasetGenerator.generate("data/input_medium.json", 3, 10, 15, 0.35, 123);
        DatasetGenerator.generate("data/input_large.json", 2, 20, 24, 0.25, 456);
        System.out.println("Datasets generated.");
    }
}
