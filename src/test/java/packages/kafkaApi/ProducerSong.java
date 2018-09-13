package packages.kafkaApi;

import io.confluent.kafka.schemaregistry.client.CachedSchemaRegistryClient;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import packages.entities.Song;

import java.util.*;

public class ProducerSong<L extends Number, S extends SpecificRecordBase> {

    private Properties props;
    private String BOOTSTRAP_SERVERS;
    private String schemaRegistryUrl;
    private List<Song> songs;
    private String TOPIC;
    private String PLAY_EVENTS;
    private CachedSchemaRegistryClient schemaRegistry;
    private Map<String, String> serdeProps;
    private KafkaProducer<Long, Song> songProducer;

    public ProducerSong() {
        this.props = new Properties();
        schemaRegistryUrl = "http://localhost:8081";
        BOOTSTRAP_SERVERS = "http://localhost:9092";
        TOPIC = "song-feed";
        PLAY_EVENTS = "play-events";
        System.out.println("Connecting to Kafka cluster via bootstrap servers " + BOOTSTRAP_SERVERS);
        System.out.println("Connecting to Confluent schema registry at " + schemaRegistryUrl);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        schemaRegistry = new CachedSchemaRegistryClient(schemaRegistryUrl, 100);
        serdeProps = Collections.singletonMap("schema.registry.url", schemaRegistryUrl);
    }

    public void createSong(){
        songs = Arrays.asList(new Song());
        SongSerializer<Song> songSerializer = new SongSerializer<>(schemaRegistry, serdeProps);
        songSerializer.configure(serdeProps, false);
        songProducer = new KafkaProducer<>(props, new LongSerializer(), songSerializer);
    }

    public void createSong(Long id, String album, String artist, String song, String genre) {
        songs = Arrays.asList(new Song(id, album, artist, song, genre));
        SongSerializer<Song> songSerializer = new SongSerializer<>(schemaRegistry, serdeProps);
        songSerializer.configure(serdeProps, false);
        songProducer = new KafkaProducer<>(props, new LongSerializer(), songSerializer);
    }

    public void sendSong() {
        songs.forEach(newSong -> {
            songProducer.send(new ProducerRecord<>(TOPIC, newSong.getId(), newSong));
        });
        songProducer.close();
    }

    public void sendSong(Long idKey) {
        songs.forEach(newSong -> {
            songProducer.send(new ProducerRecord<>(TOPIC, idKey, null));
        });
        songProducer.close();
    }
}