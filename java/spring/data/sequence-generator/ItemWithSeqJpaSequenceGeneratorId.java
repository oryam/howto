package com.oryam.howto.persistence.jpa.model;

import java.io.Serializable;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class ItemWithSeqJpaSequenceGeneratorId implements IdentifierGenerator, Configurable {

    public static final String SEQUENCE_PREFIX = "sequence_prefix";
    public static final String SEQUENCE_PREFIX_VALUE = "UID-";
    public static final String SEQUENCE_NAME = "SEQ_FOR_ITEM_CODE";

    private String sequencePrefix;

    private String sequenceCallSyntax;

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        final JdbcEnvironment jdbcEnvironment = serviceRegistry.getService(JdbcEnvironment.class);
        final Dialect dialect = jdbcEnvironment.getDialect();

        sequencePrefix = ConfigurationHelper.getString(
                                                       SEQUENCE_PREFIX,
                                                       params,
                                                       "SEQ_");

        final String sequencePerEntitySuffix = ConfigurationHelper.getString(
                                                                             SequenceStyleGenerator.CONFIG_SEQUENCE_PER_ENTITY_SUFFIX,
                                                                             params,
                                                                             SequenceStyleGenerator.DEF_SEQUENCE_SUFFIX);

        final String defaultSequenceName = ConfigurationHelper.getBoolean(
                                                                          SequenceStyleGenerator.CONFIG_PREFER_SEQUENCE_PER_ENTITY,
                                                                          params,
                                                                          false)
                                                                                 ? params.getProperty(JPA_ENTITY_NAME) + sequencePerEntitySuffix
                                                                                 : SequenceStyleGenerator.DEF_SEQUENCE_NAME;

        sequenceCallSyntax = dialect.getSequenceNextValString(
                                                              ConfigurationHelper.getString(
                                                                                            SequenceStyleGenerator.SEQUENCE_PARAM,
                                                                                            params,
                                                                                            defaultSequenceName));

    }

    @SuppressWarnings("unchecked")
    @Override
    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        if (object instanceof Identifiable) {
            Identifiable<ItemWithSeqJpa> identifiable = (Identifiable<ItemWithSeqJpa>) object;
            Serializable id = identifiable.getId();
            if (id != null) {
                return id;
            }
        }

        long nextSequenceValue = ((Number) Session.class.cast(session)
                                                        .createSQLQuery(sequenceCallSyntax)
                                                        .uniqueResult()).longValue();

        String generatedCode = generateCode(nextSequenceValue);

        return new ItemWithSeqJpaId(generatedCode, 1);
    }

    private String generateCode(long seqValue) {
        return sequencePrefix + StringUtils.leftPad(seqValue + "", 5, '0'); // UID-00000
    }

    public interface Identifiable<T extends Serializable> {
        T getId();
    }

}
