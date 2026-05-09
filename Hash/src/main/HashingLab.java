
import java.util.*;

public class HashingLab {

    /**
     * Clase: Clave y valor
     */
    static class Pair {
        String key;
        int value;
        Pair(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Clase HashTable con metodo de "Chaining"
     * Chaining: Cada indice tiene una lista enlazada en caso de colision.
     */
    static class HashTableChaining {
        private List<List<Pair>> table;
        private int size; //Tamaño de tabla sin contar tamaño de buckets
        private int count; //Cantidad de elementos insertados en la tabla
        private int collisions; //Cantidad de veces que un elemento comparte el mismo indice que otro
        private String hashStrategy;

        public HashTableChaining(int size, String hashStrategy) {
            this.size = size;
            this.hashStrategy = hashStrategy;
            this.count = 0;
            this.collisions = 0;
            this.table = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                table.add(new LinkedList<>()); //Listas enlazadas (buckets) a cada indice de la tabla
            }
        }

        /**
         * hashSum() es un metodo que se encarga de sumar los valores unicode de un caracter para luego aplicarle un MOD por el tamaño de la tabla, de esta manera garantizando que el nuevo valor caiga dentro de esta misma
         * 
         * Desventaja: Al unicamente sumar los valores unicode, no se considera el orden de estos, habiendo casos donde llaves como "abc" y "cba" den el mismo indice, provocando muchas mas colisiones.
         * @param key
         * @return Valor sumado
         */
        private int hashSum(String key) {
            int sum = 0;
            for (char c : key.toCharArray()) {
                sum += c;
            }
            return Math.floorMod(sum, size); //"abc" = "cba"
        }

        /**
         * hashPolynomial(): Es un metodo que se encarga de sumar cada valor unicode de la llave pero con una base distinta que va cambiando a medidad que transcurre el string, de esta manera evitando problemas con el orden
         * @param key
         * @return Valor sumado
         */
        private int hashPolynomial(String key) {
            //Considera orden
            int h = 0;
            int base = 31;
            for (int i = 0; i < key.length(); i++) {
                h = Math.floorMod(h * base + key.charAt(i), size);
            }
            return h;
        }

        /**
         * Funcion hash
         */
        private int hash(String key) {
            if (hashStrategy.equals("sum")) {
                return hashSum(key);
            } else if (hashStrategy.equals("polynomial")) {
                return hashPolynomial(key);
            }
            throw new IllegalArgumentException("Unknown hash strategy");
        }

        /**
         * insert(): El metodo se encarga de insertar un dato en la tabla
         * 
         * Para esto debe realizar varias acciones:
         * 
         * - Aplicar La funcion hash a la clave ingresada
         * - Comprobar si la clave ya fue usada (Y sobreescribir el valor anterior de ser asi)
         * - Verificar y sumar si hay alguna colision al momento de la insercion
         */
        public void insert(String key, int value) {
            int idx = hash(key);
            List<Pair> bucket = table.get(idx); //Bucket: Lista enlazada
            for (Pair pair : bucket) {
                if (pair.key.equals(key)) {
                    pair.value = value; //sobreescribir si la LLAVE ya esta usada
                    return;
                }
            }
            if (!bucket.isEmpty()) collisions++; //Sumar una colision si el indice ya habia salido antes
            bucket.add(new Pair(key, value)); //Añadir valor a la lista enlazada
            count++; //Sumar cantidad de elementos
        }

        /**
         * search(): Metodo encargado de buscar un valor
         * 
         * Para esto, se aplica la funcion hash a la LLAVE obteniendo el indice, luego, se busca en el BUCKET correspondiente si algun valor de estos coincide con la clave
         * @param key
         * @return
         */
        public Integer search(String key) {
            int idx = hash(key);
            //Buscar PAR del BUCKET que su LLAVE coincida con la llave ingresada
            for (Pair p : table.get(idx)) {
                if (p.key.equals(key)) return p.value;
            }
            return null;
        }

        /**
         * delete(): Metodo encargado de eliminar un Par
         * 
         * Se aplica el mismo concepto que con search(), solo que en este caso se elimina el PAR y notifica de ser asi (true o false).
         */
        public boolean delete(String key) {
            int idx = hash(key);
            List<Pair> current = table.get(idx);
            for (int i = 0; i < current.size() ; i++) {
                //Buscar PAR del BUCKET que su LLAVE coincida con la llave ingresada
                if (current.get(i).key.equals(key)) {
                    current.remove(i); //Elimina PAR
                    count--; //Descuenta 1 a la cantidad de elementos en la tabla
                    return true;
                }
            }
            return false;
        }

        /**
         * @return factor de carga, indica que tan llena esta la tabla
         */
        public double loadFactor() {
            return (double) count / size;
        }

        public int usedBuckets() {
            int used = 0;
            for (List<Pair> bucket : table) {
                if (!bucket.isEmpty()) used++;
            }
            return used;
        }

        /**
         * @return El BUCKET mas lleno de todos
         */
        public int maxBucketSize() {
            int max = 0;
            for (List<Pair> bucket : table) {
                max = Math.max(max, bucket.size());
            }
            return max;
        }

        public void printReport(double elapsedSeconds) {
            System.out.println("strategy=" + hashStrategy
                    + ", size=" + size
                    + ", elements=" + count
                    + ", loadFactor=" + String.format("%.3f", loadFactor())
                    + ", collisions=" + collisions
                    + ", usedBuckets=" + usedBuckets()
                    + ", maxBucketSize=" + maxBucketSize()
                    + ", insertTimeSeconds=" + String.format("%.6f", elapsedSeconds));
        }
    }

    static List<String> generateRandomKeys(int n, int length) {
        Random random = new Random(42);
        List<String> keys = new ArrayList<>();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < length; j++) {
                sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
            }
            keys.add(sb.toString());
        }
        return keys;
    }

    static List<String> generateSequentialKeys(int n) {
        List<String> keys = new ArrayList<>();
        for (int i = 0; i < n; i++) keys.add("user" + i);
        return keys;
    }

    static List<String> generateClusteredKeys(int n) {
        List<String> keys = new ArrayList<>();
        for (int i = 0; i < n; i++) keys.add("aaa" + i);
        return keys;
    }

    static void runExperiment(String datasetName, List<String> keys, int tableSize) {
        System.out.println("\nDataset: " + datasetName);
        for (String strategy : Arrays.asList("sum", "polynomial")) {
            HashTableChaining ht = new HashTableChaining(tableSize, strategy);
            long start = System.nanoTime();
            for (int i = 0; i < keys.size(); i++) {
                ht.insert(keys.get(i), i);
            }
            long end = System.nanoTime();
            double elapsedSeconds = (end - start) / 1_000_000_000.0;
            ht.printReport(elapsedSeconds);
        }
    }

    public static void main(String[] args) {
        int n = 1000;
        int tableSize = 211;
        runExperiment("random", generateRandomKeys(n, 8), tableSize);
        runExperiment("sequential", generateSequentialKeys(n), tableSize);
        runExperiment("clustered", generateClusteredKeys(n), tableSize);
    }
}
