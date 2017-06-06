package uk.ac.manchester.cs.owl.owlapi.concurrent;

import static org.semanticweb.owlapi.util.OWLAPIPreconditions.verifyNotNull;

import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

import javax.annotation.Nonnull;
import javax.inject.Inject;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 08/04/15
 */

import org.semanticweb.owlapi.io.OWLOntologyDocumentTarget;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.model.parameters.AxiomAnnotations;
import org.semanticweb.owlapi.model.parameters.ChangeApplied;
import org.semanticweb.owlapi.model.parameters.Imports;
import org.semanticweb.owlapi.model.parameters.Navigation;
import org.semanticweb.owlapi.util.OWLAxiomSearchFilter;
import uk.ac.manchester.cs.owl.owlapi.HasTrimToSize;

/**
 * Matthew Horridge Stanford Center for Biomedical Informatics Research 03/04/15
 */
public class ConcurrentOWLOntologyImpl implements OWLMutableOntology,HasTrimToSize {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final OWLOntology delegate;
    //private final Lock readLock;
    private final Lock writeLock;

    /**
     * Constructs a ConcurrentOWLOntology that provides concurrent access to a delegate {@link OWLOntology}.
     * @param delegate The delegate {@link OWLOntology}.
     * @param readWriteLock The {@link java.util.concurrent.locks.ReadWriteLock} that will provide the locking.
     * @throws java.lang.NullPointerException if any parameters are {@code null}.
     */
    @Inject
    public ConcurrentOWLOntologyImpl(@Nonnull OWLOntology delegate, @Nonnull ReadWriteLock readWriteLock) {
        this.delegate = verifyNotNull(delegate);
        verifyNotNull(readWriteLock);
        //this.readLock = verifyNotNull(readWriteLock).readLock();
        this.writeLock = verifyNotNull(readWriteLock).writeLock();
    }

    @Override
    public void trimToSize() {
        writeLock.lock();
        try {
            if (delegate instanceof HasTrimToSize) {
                HasTrimToSize trimmableDelegate = (HasTrimToSize) delegate;
                trimmableDelegate.trimToSize();
            }
        } finally {
            writeLock.unlock();
        }

    }

    @Override
    public void accept(@Nonnull OWLNamedObjectVisitor owlNamedObjectVisitor) {
        delegate.accept(owlNamedObjectVisitor);
    }

    @Override
    @Nonnull
    public <O> O accept(@Nonnull OWLNamedObjectVisitorEx<O> owlNamedObjectVisitorEx) {
        return delegate.accept(owlNamedObjectVisitorEx);
    }

    @Override
    public int hashCode() {
    	return delegate.hashCode();
    	/**
        readLock.lock();
        try {
            return delegate.hashCode();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean equals(Object obj) {
    	return delegate.equals(obj);
    	/**
        readLock.lock();
        try {
            return delegate.equals(obj);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public OWLOntologyManager getOWLOntologyManager() {
    	return delegate.getOWLOntologyManager();
    	/**
        readLock.lock();
        try {
            return delegate.getOWLOntologyManager();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public void setOWLOntologyManager(OWLOntologyManager owlOntologyManager) {
        writeLock.lock();
        try {
            delegate.setOWLOntologyManager(owlOntologyManager);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    @Nonnull
    public OWLOntologyID getOntologyID() {
    	return delegate.getOntologyID();
    	/**
        readLock.lock();
        try {
            return delegate.getOntologyID();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean isAnonymous() {
    	return delegate.isAnonymous();
    	/**
        readLock.lock();
        try {
            return delegate.isAnonymous();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLAnnotation> getAnnotations() {
    	return delegate.getAnnotations();
    	/*
        readLock.lock();
        try {
            return delegate.getAnnotations();
        } finally {
            readLock.unlock();
        }*/
    }

    @Override
    @Nonnull
    public Set<IRI> getDirectImportsDocuments() {
    	return delegate.getDirectImportsDocuments();
    	/**
        readLock.lock();
        try {
            return delegate.getDirectImportsDocuments();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLOntology> getDirectImports() {
    	return delegate.getDirectImports();
    	/**
        readLock.lock();
        try {
            return delegate.getDirectImports();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLOntology> getImports() {
    	return delegate.getImports();
    	/**
        readLock.lock();
        try {
            return delegate.getImports();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLOntology> getImportsClosure() {
    	return delegate.getImportsClosure();
    	/**
        readLock.lock();
        try {
            return delegate.getImportsClosure();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLImportsDeclaration> getImportsDeclarations() {
    	return delegate.getImportsDeclarations();
    	/**
        readLock.lock();
        try {
            return delegate.getImportsDeclarations();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean isEmpty() {
    	return delegate.isEmpty();
    	/**
        readLock.lock();
        try {
            return delegate.isEmpty();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLAxiom> getTBoxAxioms(@Nonnull Imports imports) {
    	return delegate.getTBoxAxioms(imports);
    	/**
        readLock.lock();
        try {
            return delegate.getTBoxAxioms(imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLAxiom> getABoxAxioms(@Nonnull Imports imports) {
    	return delegate.getABoxAxioms(imports);
    	/**
        readLock.lock();
        try {
            return delegate.getABoxAxioms(imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLAxiom> getRBoxAxioms(@Nonnull Imports imports) {
    	return delegate.getRBoxAxioms(imports);
    	/**
        readLock.lock();
        try {
            return delegate.getRBoxAxioms(imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLClassAxiom> getGeneralClassAxioms() {
    	return delegate.getGeneralClassAxioms();
    	/**
        readLock.lock();
        try {
            return delegate.getGeneralClassAxioms();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLEntity> getSignature() {
    	return delegate.getSignature();
    	/**
        readLock.lock();
        try {
            return delegate.getSignature();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLEntity> getSignature(@Nonnull Imports imports) {
    	return delegate.getSignature(imports);
    	/**
        readLock.lock();
        try {
            return delegate.getSignature(imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean isDeclared(@Nonnull OWLEntity owlEntity) {
    	return delegate.isDeclared(owlEntity);
    	/**
        readLock.lock();
        try {
            return delegate.isDeclared(owlEntity);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean isDeclared(@Nonnull OWLEntity owlEntity, @Nonnull Imports imports) {
    	return delegate.isDeclared(owlEntity, imports);
    	/**
        readLock.lock();
        try {
            return delegate.isDeclared(owlEntity, imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public void saveOntology() throws OWLOntologyStorageException {
    	delegate.saveOntology();
    	/**
        readLock.lock();
        try {
            delegate.saveOntology();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public void saveOntology(@Nonnull IRI iri) throws OWLOntologyStorageException {
    	delegate.saveOntology(iri);
    	/**
        readLock.lock();
        try {
            delegate.saveOntology(iri);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public void saveOntology(@Nonnull OutputStream outputStream) throws OWLOntologyStorageException {
    	delegate.saveOntology(outputStream);
    	/**
        readLock.lock();
        try {
            delegate.saveOntology(outputStream);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public void saveOntology(@Nonnull OWLDocumentFormat owlDocumentFormat) throws OWLOntologyStorageException {
    	delegate.saveOntology(owlDocumentFormat);
    	/**
        readLock.lock();
        try {
            delegate.saveOntology(owlDocumentFormat);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public void saveOntology(@Nonnull OWLDocumentFormat owlDocumentFormat, @Nonnull IRI iri) throws OWLOntologyStorageException {
    	delegate.saveOntology(owlDocumentFormat, iri);
    	/**
        readLock.lock();
        try {
            delegate.saveOntology(owlDocumentFormat, iri);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public void saveOntology(@Nonnull OWLDocumentFormat owlDocumentFormat, @Nonnull OutputStream outputStream) throws OWLOntologyStorageException {
    	delegate.saveOntology(owlDocumentFormat, outputStream);
    	/**
    	readLock.lock();
        try {
            delegate.saveOntology(owlDocumentFormat, outputStream);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public void saveOntology(@Nonnull OWLOntologyDocumentTarget owlOntologyDocumentTarget) throws OWLOntologyStorageException {
    	delegate.saveOntology(owlOntologyDocumentTarget);
    	/**
    	readLock.lock();
        try {
            delegate.saveOntology(owlOntologyDocumentTarget);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public void saveOntology(@Nonnull OWLDocumentFormat owlDocumentFormat, @Nonnull OWLOntologyDocumentTarget owlOntologyDocumentTarget) throws OWLOntologyStorageException {
    	delegate.saveOntology(owlDocumentFormat, owlOntologyDocumentTarget);
    	/**
    	readLock.lock();
        try {
            delegate.saveOntology(owlDocumentFormat, owlOntologyDocumentTarget);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLClassExpression> getNestedClassExpressions() {
    	return delegate.getNestedClassExpressions();
    	/**
        readLock.lock();
        try {
            return delegate.getNestedClassExpressions();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public void accept(@Nonnull OWLObjectVisitor owlObjectVisitor) {
        delegate.accept(owlObjectVisitor);
    }

    @Override
    @Nonnull
    public <O> O accept(@Nonnull OWLObjectVisitorEx<O> owlObjectVisitorEx) {
        return delegate.accept(owlObjectVisitorEx);
    }

    @Override
    public boolean isTopEntity() {
    	return delegate.isTopEntity();
    	/**
        readLock.lock();
        try {
            return delegate.isTopEntity();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean isBottomEntity() {
    	return delegate.isBottomEntity();
    	/**
        readLock.lock();
        try {
            return delegate.isBottomEntity();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public String toString() {
    	return delegate.toString();
    	/**
        readLock.lock();
        try {
            return delegate.toString();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public int compareTo(OWLObject o) {
    	return delegate.compareTo(o);
    	/**
        readLock.lock();
        try {
            return delegate.compareTo(o);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean containsEntityInSignature(@Nonnull OWLEntity owlEntity) {
    	return delegate.containsEntityInSignature(owlEntity);
    	/**
        readLock.lock();
        try {
            return delegate.containsEntityInSignature(owlEntity);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLAnonymousIndividual> getAnonymousIndividuals() {
    	return delegate.getAnonymousIndividuals();
    	/**
        readLock.lock();
        try {
            return delegate.getAnonymousIndividuals();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLClass> getClassesInSignature() {
    	return delegate.getClassesInSignature();
    	/**
        readLock.lock();
        try {
            return delegate.getClassesInSignature();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLObjectProperty> getObjectPropertiesInSignature() {
    	return delegate.getObjectPropertiesInSignature();
    	/**
        readLock.lock();
        try {
            return delegate.getObjectPropertiesInSignature();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLDataProperty> getDataPropertiesInSignature() {
    	return delegate.getDataPropertiesInSignature();
    	/**
        readLock.lock();
        try {
            return delegate.getDataPropertiesInSignature();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLNamedIndividual> getIndividualsInSignature() {
    	return delegate.getIndividualsInSignature();
    	/**
        readLock.lock();
        try {
            return delegate.getIndividualsInSignature();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLDatatype> getDatatypesInSignature() {
    	return delegate.getDatatypesInSignature();
    	/**
        readLock.lock();
        try {
            return delegate.getDatatypesInSignature();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLAnnotationProperty> getAnnotationPropertiesInSignature() {
    	return delegate.getAnnotationPropertiesInSignature();
    	/**
        readLock.lock();
        try {
            return delegate.getAnnotationPropertiesInSignature();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLAxiom> getAxioms(@Nonnull Imports imports) {
    	return delegate.getAxioms(imports);
    	/**
        readLock.lock();
        try {
            return delegate.getAxioms(imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public int getAxiomCount(@Nonnull Imports imports) {
    	return delegate.getAxiomCount(imports);
    	/**
        readLock.lock();
        try {
            return delegate.getAxiomCount(imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLLogicalAxiom> getLogicalAxioms(@Nonnull Imports imports) {
    	return delegate.getLogicalAxioms(imports);
    	/**
        readLock.lock();
        try {
            return delegate.getLogicalAxioms(imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public int getLogicalAxiomCount(@Nonnull Imports imports) {
    	return delegate.getLogicalAxiomCount(imports);
    	/**
        readLock.lock();
        try {
            return delegate.getLogicalAxiomCount(imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public <T extends OWLAxiom> Set<T> getAxioms(@Nonnull AxiomType<T> axiomType, @Nonnull Imports imports) {
    	return delegate.getAxioms(axiomType, imports);
    	/**
        readLock.lock();
        try {
            return delegate.getAxioms(axiomType, imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public <T extends OWLAxiom> int getAxiomCount(@Nonnull AxiomType<T> axiomType, @Nonnull Imports imports) {
    	return delegate.getAxiomCount(axiomType, imports);
    	/**
        readLock.lock();
        try {
            return delegate.getAxiomCount(axiomType, imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean containsAxiom(@Nonnull OWLAxiom owlAxiom, @Nonnull Imports imports, @Nonnull AxiomAnnotations axiomAnnotations) {
    	return delegate.containsAxiom(owlAxiom, imports, axiomAnnotations);
    	/**
        readLock.lock();
        try {
            return delegate.containsAxiom(owlAxiom, imports, axiomAnnotations);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLAxiom> getAxiomsIgnoreAnnotations(@Nonnull OWLAxiom owlAxiom, @Nonnull Imports imports) {
    	return delegate.getAxiomsIgnoreAnnotations(owlAxiom, imports);
    	/**
        readLock.lock();
        try {
            return delegate.getAxiomsIgnoreAnnotations(owlAxiom, imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLAxiom> getReferencingAxioms(@Nonnull OWLPrimitive owlPrimitive, @Nonnull Imports imports) {
    	return delegate.getReferencingAxioms(owlPrimitive, imports);
    	/**
        readLock.lock();
        try {
            return delegate.getReferencingAxioms(owlPrimitive, imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLClassAxiom> getAxioms(@Nonnull OWLClass owlClass, @Nonnull Imports imports) {
    	return delegate.getAxioms(owlClass, imports);
    	/**
        readLock.lock();
        try {
            return delegate.getAxioms(owlClass, imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLObjectPropertyAxiom> getAxioms(@Nonnull OWLObjectPropertyExpression owlObjectPropertyExpression, @Nonnull Imports imports) {
    	return delegate.getAxioms(owlObjectPropertyExpression, imports);
    	/**
        readLock.lock();
        try {
            return delegate.getAxioms(owlObjectPropertyExpression, imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLDataPropertyAxiom> getAxioms(@Nonnull OWLDataProperty owlDataProperty, @Nonnull Imports imports) {
    	return delegate.getAxioms(owlDataProperty, imports);
    	/**
        readLock.lock();
        try {
            return delegate.getAxioms(owlDataProperty, imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLIndividualAxiom> getAxioms(@Nonnull OWLIndividual owlIndividual, @Nonnull Imports imports) {
    	return delegate.getAxioms(owlIndividual, imports);
    	/**
        readLock.lock();
        try {
            return delegate.getAxioms(owlIndividual, imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLAnnotationAxiom> getAxioms(@Nonnull OWLAnnotationProperty owlAnnotationProperty, @Nonnull Imports imports) {
    	return delegate.getAxioms(owlAnnotationProperty, imports);
    	/**
    	readLock.lock();
        try {
            return delegate.getAxioms(owlAnnotationProperty, imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLDatatypeDefinitionAxiom> getAxioms(@Nonnull OWLDatatype owlDatatype, @Nonnull Imports imports) {
    	return delegate.getAxioms(owlDatatype, imports);
    	/**
    	readLock.lock();
        try {
            return delegate.getAxioms(owlDatatype, imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLAxiom> getAxioms() {
    	return delegate.getAxioms();
    	/**
        readLock.lock();
        try {
            return delegate.getAxioms();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLLogicalAxiom> getLogicalAxioms() {
    	return delegate.getLogicalAxioms();
    	/**
        readLock.lock();
        try {
            return delegate.getLogicalAxioms();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public <T extends OWLAxiom> Set<T> getAxioms(@Nonnull AxiomType<T> axiomType) {
    	return delegate.getAxioms(axiomType);
    	/**
        readLock.lock();
        try {
            return delegate.getAxioms(axiomType);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean containsAxiom(@Nonnull OWLAxiom owlAxiom) {
    	return delegate.containsAxiom(owlAxiom);
    	/**
        readLock.lock();
        try {
            return delegate.containsAxiom(owlAxiom);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    @Deprecated
    public Set<OWLAxiom> getAxioms(boolean b) {
    	return delegate.getAxioms(b);
    	/**
        readLock.lock();
        try {
            return delegate.getAxioms(b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    public int getAxiomCount(boolean b) {
    	return delegate.getAxiomCount(b);
    	/**
        readLock.lock();
        try {
            return delegate.getAxiomCount(b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    @Nonnull
    public Set<OWLLogicalAxiom> getLogicalAxioms(boolean b) {
    	return delegate.getLogicalAxioms(b);
    	/**
        readLock.lock();
        try {
            return delegate.getLogicalAxioms(b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    public int getLogicalAxiomCount(boolean b) {
    	return delegate.getLogicalAxiomCount(b);
    	/**
        readLock.lock();
        try {
            return delegate.getLogicalAxiomCount(b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    @Nonnull
    public <T extends OWLAxiom> Set<T> getAxioms(@Nonnull AxiomType<T> axiomType, boolean b) {
    	return delegate.getAxioms(axiomType, b);
    	/**
        readLock.lock();
        try {
            return delegate.getAxioms(axiomType, b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    public <T extends OWLAxiom> int getAxiomCount(@Nonnull AxiomType<T> axiomType, boolean b) {
    	return delegate.getAxiomCount(axiomType, b);
    	/**
        readLock.lock();
        try {
            return delegate.getAxiomCount(axiomType, b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    public boolean containsAxiom(@Nonnull OWLAxiom owlAxiom, boolean b) {
    	return delegate.containsAxiom(owlAxiom, b);    	
    	/**
        readLock.lock();
        try {
            return delegate.containsAxiom(owlAxiom, b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    public boolean containsAxiomIgnoreAnnotations(@Nonnull OWLAxiom owlAxiom, boolean b) {
    	return delegate.containsAxiomIgnoreAnnotations(owlAxiom, b);
    	/**
        readLock.lock();
        try {
            return delegate.containsAxiomIgnoreAnnotations(owlAxiom, b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    @Nonnull
    public Set<OWLAxiom> getAxiomsIgnoreAnnotations(@Nonnull OWLAxiom owlAxiom, boolean b) {
    	return delegate.getAxiomsIgnoreAnnotations(owlAxiom, b);
    	/**
        readLock.lock();
        try {
            return delegate.getAxiomsIgnoreAnnotations(owlAxiom, b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    @Nonnull
    public Set<OWLAxiom> getReferencingAxioms(@Nonnull OWLPrimitive owlPrimitive, boolean b) {
    	return delegate.getReferencingAxioms(owlPrimitive, b);
    	/**
        readLock.lock();
        try {
            return delegate.getReferencingAxioms(owlPrimitive, b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    @Deprecated
    public Set<OWLClassAxiom> getAxioms(@Nonnull OWLClass owlClass, boolean b) {
    	return delegate.getAxioms(owlClass, b);
    	/**
        readLock.lock();
        try {
            return delegate.getAxioms(owlClass, b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    @Deprecated
    public Set<OWLObjectPropertyAxiom> getAxioms(@Nonnull OWLObjectPropertyExpression owlObjectPropertyExpression, boolean b) {
    	return delegate.getAxioms(owlObjectPropertyExpression, b);
    	/**
    	readLock.lock();
        try {
            return delegate.getAxioms(owlObjectPropertyExpression, b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    @Deprecated
    public Set<OWLDataPropertyAxiom> getAxioms(@Nonnull OWLDataProperty owlDataProperty, boolean b) {
    	return delegate.getAxioms(owlDataProperty, b);
    	/**
        readLock.lock();
        try {
            return delegate.getAxioms(owlDataProperty, b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    @Deprecated
    public Set<OWLIndividualAxiom> getAxioms(@Nonnull OWLIndividual owlIndividual, boolean b) {
    	return delegate.getAxioms(owlIndividual, b);
    	/**
        readLock.lock();
        try {
            return delegate.getAxioms(owlIndividual, b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    @Deprecated
    public Set<OWLAnnotationAxiom> getAxioms(@Nonnull OWLAnnotationProperty owlAnnotationProperty, boolean b) {
    	return delegate.getAxioms(owlAnnotationProperty, b);
    	/**
        readLock.lock();
        try {
            return delegate.getAxioms(owlAnnotationProperty, b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    @Deprecated
    public Set<OWLDatatypeDefinitionAxiom> getAxioms(@Nonnull OWLDatatype owlDatatype, boolean b) {
    	return delegate.getAxioms(owlDatatype, b);
    	/**
        readLock.lock();
        try {
            return delegate.getAxioms(owlDatatype, b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public int getAxiomCount() {
    	return delegate.getAxiomCount();
    	/**
        readLock.lock();
        try {
            return delegate.getAxiomCount();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public int getLogicalAxiomCount() {
    	return delegate.getLogicalAxiomCount();
    	/**
        readLock.lock();
        try {
            return delegate.getLogicalAxiomCount();
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public <T extends OWLAxiom> int getAxiomCount(@Nonnull AxiomType<T> axiomType) {
    	return delegate.getAxiomCount(axiomType);
    	/**
        readLock.lock();
        try {
            return delegate.getAxiomCount(axiomType);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean containsAxiomIgnoreAnnotations(@Nonnull OWLAxiom owlAxiom) {
    	return delegate.containsAxiomIgnoreAnnotations(owlAxiom);
    	/**
        readLock.lock();
        try {
            return delegate.containsAxiomIgnoreAnnotations(owlAxiom);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLAxiom> getAxiomsIgnoreAnnotations(@Nonnull OWLAxiom owlAxiom) {
    	return delegate.getAxiomsIgnoreAnnotations(owlAxiom);
    	/**
        readLock.lock();
        try {
            return delegate.getAxiomsIgnoreAnnotations(owlAxiom);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLAxiom> getReferencingAxioms(@Nonnull OWLPrimitive owlPrimitive) {
    	return delegate.getReferencingAxioms(owlPrimitive);
    	/**
        readLock.lock();
        try {
            return delegate.getReferencingAxioms(owlPrimitive);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    @Deprecated
    public Set<OWLClassAxiom> getAxioms(@Nonnull OWLClass owlClass) {
    	return delegate.getAxioms(owlClass);
    	/**
        readLock.lock();
        try {
            return delegate.getAxioms(owlClass);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    @Deprecated
    public Set<OWLObjectPropertyAxiom> getAxioms(@Nonnull OWLObjectPropertyExpression owlObjectPropertyExpression) {
    	return delegate.getAxioms(owlObjectPropertyExpression);
    	/**
        readLock.lock();
        try {
            return delegate.getAxioms(owlObjectPropertyExpression);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    @Deprecated
    public Set<OWLDataPropertyAxiom> getAxioms(@Nonnull OWLDataProperty owlDataProperty) {
    	return delegate.getAxioms(owlDataProperty);
    	/**
        readLock.lock();
        try {
            return delegate.getAxioms(owlDataProperty);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    @Deprecated
    public Set<OWLIndividualAxiom> getAxioms(@Nonnull OWLIndividual owlIndividual) {
    	return delegate.getAxioms(owlIndividual);
    	/**
        readLock.lock();
        try {
            return delegate.getAxioms(owlIndividual);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    @Deprecated
    public Set<OWLAnnotationAxiom> getAxioms(@Nonnull OWLAnnotationProperty owlAnnotationProperty) {
    	return delegate.getAxioms(owlAnnotationProperty);
    	/**
        readLock.lock();
        try {
            return delegate.getAxioms(owlAnnotationProperty);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    @Deprecated
    public Set<OWLDatatypeDefinitionAxiom> getAxioms(@Nonnull OWLDatatype owlDatatype) {
    	return delegate.getAxioms(owlDatatype);
    	/**
        readLock.lock();
        try {
            return delegate.getAxioms(owlDatatype);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLClass> getClassesInSignature(@Nonnull Imports imports) {    	
    	return delegate.getClassesInSignature(imports);
    	/**
        readLock.lock();
        try {
            return delegate.getClassesInSignature(imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLObjectProperty> getObjectPropertiesInSignature(@Nonnull Imports imports) {
    	return delegate.getObjectPropertiesInSignature(imports);
    	/**
        readLock.lock();
        try {
            return delegate.getObjectPropertiesInSignature(imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLDataProperty> getDataPropertiesInSignature(@Nonnull Imports imports) {
    	return delegate.getDataPropertiesInSignature(imports);
    	/**
        readLock.lock();
        try {
            return delegate.getDataPropertiesInSignature(imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLNamedIndividual> getIndividualsInSignature(@Nonnull Imports imports) {
    	return delegate.getIndividualsInSignature(imports);
    	/**
        readLock.lock();
        try {
            return delegate.getIndividualsInSignature(imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLAnonymousIndividual> getReferencedAnonymousIndividuals(@Nonnull Imports imports) {
    	return delegate.getReferencedAnonymousIndividuals(imports);
    	/**
        readLock.lock();
        try {
            return delegate.getReferencedAnonymousIndividuals(imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLDatatype> getDatatypesInSignature(@Nonnull Imports imports) {
    	return delegate.getDatatypesInSignature(imports);
    	/**
        readLock.lock();
        try {
            return delegate.getDatatypesInSignature(imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLAnnotationProperty> getAnnotationPropertiesInSignature(@Nonnull Imports imports) {
    	return delegate.getAnnotationPropertiesInSignature(imports);
    	/**
        readLock.lock();
        try {
            return delegate.getAnnotationPropertiesInSignature(imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean containsEntityInSignature(@Nonnull OWLEntity owlEntity, @Nonnull Imports imports) {
    	return delegate.containsEntityInSignature(owlEntity, imports);
    	/**
        readLock.lock();
        try {
            return delegate.containsEntityInSignature(owlEntity, imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean containsEntityInSignature(@Nonnull IRI iri, @Nonnull Imports imports) {
    	return delegate.containsEntityInSignature(iri, imports);
    	/**
        readLock.lock();
        try {
            return delegate.containsEntityInSignature(iri, imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean containsClassInSignature(@Nonnull IRI iri, @Nonnull Imports imports) {
    	return delegate.containsClassInSignature(iri, imports);
    	/**
        readLock.lock();
        try {
            return delegate.containsClassInSignature(iri, imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean containsObjectPropertyInSignature(@Nonnull IRI iri, @Nonnull Imports imports) {
    	return delegate.containsObjectPropertyInSignature(iri, imports);
    	/**
        readLock.lock();
        try {
            return delegate.containsObjectPropertyInSignature(iri, imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean containsDataPropertyInSignature(@Nonnull IRI iri, @Nonnull Imports imports) {
    	return delegate.containsDataPropertyInSignature(iri, imports);
    	/**
        readLock.lock();
        try {
            return delegate.containsDataPropertyInSignature(iri, imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean containsAnnotationPropertyInSignature(@Nonnull IRI iri, @Nonnull Imports imports) {
    	return delegate.containsAnnotationPropertyInSignature(iri, imports);
    	/**
        readLock.lock();
        try {
            return delegate.containsAnnotationPropertyInSignature(iri, imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean containsDatatypeInSignature(@Nonnull IRI iri, @Nonnull Imports imports) {
    	return delegate.containsDatatypeInSignature(iri, imports);
    	/**
        readLock.lock();
        try {
            return delegate.containsDatatypeInSignature(iri, imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean containsIndividualInSignature(@Nonnull IRI iri, @Nonnull Imports imports) {
    	return delegate.containsIndividualInSignature(iri, imports);
    	/**
        readLock.lock();
        try {
            return delegate.containsIndividualInSignature(iri, imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean containsDatatypeInSignature(@Nonnull IRI iri) {
    	return delegate.containsDatatypeInSignature(iri);
    	/**
        readLock.lock();
        try {
            return delegate.containsDatatypeInSignature(iri);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean containsEntityInSignature(@Nonnull IRI iri) {
    	return delegate.containsEntityInSignature(iri);
    	/**
        readLock.lock();
        try {
            return delegate.containsEntityInSignature(iri);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean containsClassInSignature(@Nonnull IRI iri) {
    	return delegate.containsClassInSignature(iri);
    	/**
        readLock.lock();
        try {
            return delegate.containsClassInSignature(iri);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean containsObjectPropertyInSignature(@Nonnull IRI iri) {
    	return delegate.containsObjectPropertyInSignature(iri);
    	/**
        readLock.lock();
        try {
            return delegate.containsObjectPropertyInSignature(iri);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean containsDataPropertyInSignature(@Nonnull IRI iri) {
    	return delegate.containsDataPropertyInSignature(iri);
    	/**
        readLock.lock();
        try {
            return delegate.containsDataPropertyInSignature(iri);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean containsAnnotationPropertyInSignature(@Nonnull IRI iri) {
    	return delegate.containsAnnotationPropertyInSignature(iri);
    	/**
        readLock.lock();
        try {
            return delegate.containsAnnotationPropertyInSignature(iri);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean containsIndividualInSignature(@Nonnull IRI iri) {
    	return delegate.containsIndividualInSignature(iri);
    	/**
        readLock.lock();
        try {
            return delegate.containsIndividualInSignature(iri);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLEntity> getEntitiesInSignature(@Nonnull IRI iri, @Nonnull Imports imports) {
    	return delegate.getEntitiesInSignature(iri, imports);
    	/**
        readLock.lock();
        try {
            return delegate.getEntitiesInSignature(iri, imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public Set<IRI> getPunnedIRIs(@Nonnull Imports imports) {
    	return delegate.getPunnedIRIs(imports);
    	/**
        readLock.lock();
        try {
            return delegate.getPunnedIRIs(imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean containsReference(@Nonnull OWLEntity owlEntity, @Nonnull Imports imports) {
    	return delegate.containsReference(owlEntity, imports);
    	/**
        readLock.lock();
        try {
            return delegate.containsReference(owlEntity, imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean containsReference(@Nonnull OWLEntity owlEntity) {
    	return delegate.containsReference(owlEntity);
    	/**
        readLock.lock();
        try {
            return delegate.containsReference(owlEntity);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLEntity> getEntitiesInSignature(@Nonnull IRI iri) {
    	return delegate.getEntitiesInSignature(iri);
    	/**
        readLock.lock();
        try {
            return delegate.getEntitiesInSignature(iri);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    @Nonnull
    public Set<OWLClass> getClassesInSignature(boolean b) {
    	return delegate.getClassesInSignature(b);
    	/**
        readLock.lock();
        try {
            return delegate.getClassesInSignature(b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    @Nonnull
    public Set<OWLObjectProperty> getObjectPropertiesInSignature(boolean b) {
    	return delegate.getObjectPropertiesInSignature(b);
    	/**
        readLock.lock();
        try {
            return delegate.getObjectPropertiesInSignature(b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    @Nonnull
    public Set<OWLDataProperty> getDataPropertiesInSignature(boolean b) {
    	return delegate.getDataPropertiesInSignature(b);
    	/**
        readLock.lock();
        try {
            return delegate.getDataPropertiesInSignature(b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    @Nonnull
    public Set<OWLNamedIndividual> getIndividualsInSignature(boolean b) {
    	return delegate.getIndividualsInSignature(b);
    	/**
        readLock.lock();
        try {
            return delegate.getIndividualsInSignature(b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    @Nonnull
    public Set<OWLAnonymousIndividual> getReferencedAnonymousIndividuals(boolean b) {
    	return delegate.getReferencedAnonymousIndividuals(b);
    	/**
        readLock.lock();
        try {
            return delegate.getReferencedAnonymousIndividuals(b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    @Nonnull
    public Set<OWLDatatype> getDatatypesInSignature(boolean b) {
    	return delegate.getDatatypesInSignature(b);
    	/**
        readLock.lock();
        try {
            return delegate.getDatatypesInSignature(b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    @Nonnull
    public Set<OWLAnnotationProperty> getAnnotationPropertiesInSignature(boolean b) {
    	return delegate.getAnnotationPropertiesInSignature(b);
    	/**
        readLock.lock();
        try {
            return delegate.getAnnotationPropertiesInSignature(b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    public boolean containsEntityInSignature(@Nonnull OWLEntity owlEntity, boolean b) {
    	return delegate.containsEntityInSignature(owlEntity, b);
    	/**
        readLock.lock();
        try {
            return delegate.containsEntityInSignature(owlEntity, b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    public boolean containsEntityInSignature(@Nonnull IRI iri, boolean b) {
    	return delegate.containsEntityInSignature(iri, b);
    	/**
        readLock.lock();
        try {
            return delegate.containsEntityInSignature(iri, b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    public boolean containsClassInSignature(@Nonnull IRI iri, boolean b) {
    	return delegate.containsClassInSignature(iri, b);
    	/**
        readLock.lock();
        try {
            return delegate.containsClassInSignature(iri, b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    public boolean containsObjectPropertyInSignature(@Nonnull IRI iri, boolean b) {
    	return delegate.containsObjectPropertyInSignature(iri, b);
    	/**
        readLock.lock();
        try {
            return delegate.containsObjectPropertyInSignature(iri, b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    public boolean containsDataPropertyInSignature(@Nonnull IRI iri, boolean b) {
    	return delegate.containsDataPropertyInSignature(iri, b);
    	/**
        readLock.lock();
        try {
            return delegate.containsDataPropertyInSignature(iri, b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    public boolean containsAnnotationPropertyInSignature(@Nonnull IRI iri, boolean b) {
    	return delegate.containsAnnotationPropertyInSignature(iri, b);
    	/**
        readLock.lock();
        try {
            return delegate.containsAnnotationPropertyInSignature(iri, b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    public boolean containsDatatypeInSignature(@Nonnull IRI iri, boolean b) {
    	return delegate.containsDatatypeInSignature(iri, b);
    	/**
        readLock.lock();
        try {
            return delegate.containsDatatypeInSignature(iri, b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    public boolean containsIndividualInSignature(@Nonnull IRI iri, boolean b) {
    	return delegate.containsIndividualInSignature(iri, b);
    	/**
        readLock.lock();
        try {
            return delegate.containsIndividualInSignature(iri, b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    @Nonnull
    public Set<OWLEntity> getEntitiesInSignature(@Nonnull IRI iri, boolean b) {
    	return delegate.getEntitiesInSignature(iri, b);
    	/**
        readLock.lock();
        try {
            return delegate.getEntitiesInSignature(iri, b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Deprecated
    public boolean containsReference(@Nonnull OWLEntity owlEntity, boolean b) {
    	return delegate.containsReference(owlEntity, b);
    	/**
        readLock.lock();
        try {
            return delegate.containsReference(owlEntity, b);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public <T extends OWLAxiom> Set<T> getAxioms(@Nonnull Class<T> aClass, @Nonnull OWLObject owlObject, @Nonnull Imports imports, @Nonnull Navigation navigation) {
    	return delegate.getAxioms(aClass, owlObject, imports, navigation);
    	/**
    	readLock.lock();
        try {
            return delegate.getAxioms(aClass, owlObject, imports, navigation);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public <T extends OWLAxiom> Collection<T> filterAxioms(@Nonnull OWLAxiomSearchFilter owlAxiomSearchFilter, @Nonnull Object o, @Nonnull Imports imports) {
    	return delegate.filterAxioms(owlAxiomSearchFilter, o, imports);
    	/**
    	readLock.lock();
        try {
            return delegate.filterAxioms(owlAxiomSearchFilter, o, imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    public boolean contains(@Nonnull OWLAxiomSearchFilter owlAxiomSearchFilter, @Nonnull Object o, @Nonnull Imports imports) {
    	return delegate.contains(owlAxiomSearchFilter, o, imports);
    	/**
    	readLock.lock();
        try {
            return delegate.contains(owlAxiomSearchFilter, o, imports);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public <T extends OWLAxiom> Set<T> getAxioms(@Nonnull Class<T> aClass, @Nonnull Class<? extends OWLObject> aClass1, @Nonnull OWLObject owlObject, @Nonnull Imports imports, @Nonnull Navigation navigation) {
    	return delegate.getAxioms(aClass, aClass1, owlObject, imports, navigation);
    	/**
    	readLock.lock();
        try {
            return delegate.getAxioms(aClass, aClass1, owlObject, imports, navigation);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLSubAnnotationPropertyOfAxiom> getSubAnnotationPropertyOfAxioms(@Nonnull OWLAnnotationProperty owlAnnotationProperty) {
    	return delegate.getSubAnnotationPropertyOfAxioms(owlAnnotationProperty);
    	/**
    	readLock.lock();
        try {
            return delegate.getSubAnnotationPropertyOfAxioms(owlAnnotationProperty);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLAnnotationPropertyDomainAxiom> getAnnotationPropertyDomainAxioms(@Nonnull OWLAnnotationProperty owlAnnotationProperty) {
    	return delegate.getAnnotationPropertyDomainAxioms(owlAnnotationProperty);
    	/**
    	readLock.lock();
        try {
            return delegate.getAnnotationPropertyDomainAxioms(owlAnnotationProperty);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLAnnotationPropertyRangeAxiom> getAnnotationPropertyRangeAxioms(@Nonnull OWLAnnotationProperty owlAnnotationProperty) {
    	return delegate.getAnnotationPropertyRangeAxioms(owlAnnotationProperty);
    	/**
    	readLock.lock();
        try {
            return delegate.getAnnotationPropertyRangeAxioms(owlAnnotationProperty);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLDeclarationAxiom> getDeclarationAxioms(@Nonnull OWLEntity owlEntity) {
    	return delegate.getDeclarationAxioms(owlEntity);
    	/**
        readLock.lock();
        try {
            return delegate.getDeclarationAxioms(owlEntity);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLAnnotationAssertionAxiom> getAnnotationAssertionAxioms(@Nonnull OWLAnnotationSubject owlAnnotationSubject) {
    	return delegate.getAnnotationAssertionAxioms(owlAnnotationSubject);
    	/**
    	readLock.lock();
        try {
            return delegate.getAnnotationAssertionAxioms(owlAnnotationSubject);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLSubClassOfAxiom> getSubClassAxiomsForSubClass(@Nonnull OWLClass owlClass) {
    	return delegate.getSubClassAxiomsForSubClass(owlClass);
    	/**
        readLock.lock();
        try {
            return delegate.getSubClassAxiomsForSubClass(owlClass);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLSubClassOfAxiom> getSubClassAxiomsForSuperClass(@Nonnull OWLClass owlClass) {
    	return delegate.getSubClassAxiomsForSuperClass(owlClass);
    	/**
        readLock.lock();
        try {
            return delegate.getSubClassAxiomsForSuperClass(owlClass);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLEquivalentClassesAxiom> getEquivalentClassesAxioms(@Nonnull OWLClass owlClass) {
    	return delegate.getEquivalentClassesAxioms(owlClass);
    	/**
        readLock.lock();
        try {
            return delegate.getEquivalentClassesAxioms(owlClass);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLDisjointClassesAxiom> getDisjointClassesAxioms(@Nonnull OWLClass owlClass) {
    	return delegate.getDisjointClassesAxioms(owlClass);
    	/**
        readLock.lock();
        try {
            return delegate.getDisjointClassesAxioms(owlClass);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLDisjointUnionAxiom> getDisjointUnionAxioms(@Nonnull OWLClass owlClass) {
    	return delegate.getDisjointUnionAxioms(owlClass);
    	/**
        readLock.lock();
        try {
            return delegate.getDisjointUnionAxioms(owlClass);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLHasKeyAxiom> getHasKeyAxioms(@Nonnull OWLClass owlClass) {
    	return delegate.getHasKeyAxioms(owlClass);
    	/**
        readLock.lock();
        try {
            return delegate.getHasKeyAxioms(owlClass);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLSubObjectPropertyOfAxiom> getObjectSubPropertyAxiomsForSubProperty(@Nonnull OWLObjectPropertyExpression owlObjectPropertyExpression) {
    	return delegate.getObjectSubPropertyAxiomsForSubProperty(owlObjectPropertyExpression);
    	/**
        readLock.lock();
        try {
            return delegate.getObjectSubPropertyAxiomsForSubProperty(owlObjectPropertyExpression);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLSubObjectPropertyOfAxiom> getObjectSubPropertyAxiomsForSuperProperty(@Nonnull OWLObjectPropertyExpression owlObjectPropertyExpression) {
    	return delegate.getObjectSubPropertyAxiomsForSuperProperty(owlObjectPropertyExpression);
    	/**
    	readLock.lock();
        try {
            return delegate.getObjectSubPropertyAxiomsForSuperProperty(owlObjectPropertyExpression);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLObjectPropertyDomainAxiom> getObjectPropertyDomainAxioms(@Nonnull OWLObjectPropertyExpression owlObjectPropertyExpression) {
    	return delegate.getObjectPropertyDomainAxioms(owlObjectPropertyExpression);
    	/**
    	readLock.lock();
        try {
            return delegate.getObjectPropertyDomainAxioms(owlObjectPropertyExpression);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLObjectPropertyRangeAxiom> getObjectPropertyRangeAxioms(@Nonnull OWLObjectPropertyExpression owlObjectPropertyExpression) {
    	return delegate.getObjectPropertyRangeAxioms(owlObjectPropertyExpression);
    	/**
    	readLock.lock();
        try {
            return delegate.getObjectPropertyRangeAxioms(owlObjectPropertyExpression);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLInverseObjectPropertiesAxiom> getInverseObjectPropertyAxioms(@Nonnull OWLObjectPropertyExpression owlObjectPropertyExpression) {
    	return delegate.getInverseObjectPropertyAxioms(owlObjectPropertyExpression);
    	/**
    	readLock.lock();
        try {
            return delegate.getInverseObjectPropertyAxioms(owlObjectPropertyExpression);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLEquivalentObjectPropertiesAxiom> getEquivalentObjectPropertiesAxioms(@Nonnull OWLObjectPropertyExpression owlObjectPropertyExpression) {
    	return delegate.getEquivalentObjectPropertiesAxioms(owlObjectPropertyExpression);
    	/**
    	readLock.lock();
        try {
            return delegate.getEquivalentObjectPropertiesAxioms(owlObjectPropertyExpression);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLDisjointObjectPropertiesAxiom> getDisjointObjectPropertiesAxioms(@Nonnull OWLObjectPropertyExpression owlObjectPropertyExpression) {
    	return delegate.getDisjointObjectPropertiesAxioms(owlObjectPropertyExpression);
    	/**
    	readLock.lock();
        try {
            return delegate.getDisjointObjectPropertiesAxioms(owlObjectPropertyExpression);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLFunctionalObjectPropertyAxiom> getFunctionalObjectPropertyAxioms(@Nonnull OWLObjectPropertyExpression owlObjectPropertyExpression) {
    	return delegate.getFunctionalObjectPropertyAxioms(owlObjectPropertyExpression);
    	/**
    	readLock.lock();
        try {
            return delegate.getFunctionalObjectPropertyAxioms(owlObjectPropertyExpression);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLInverseFunctionalObjectPropertyAxiom> getInverseFunctionalObjectPropertyAxioms(@Nonnull OWLObjectPropertyExpression owlObjectPropertyExpression) {
    	return delegate.getInverseFunctionalObjectPropertyAxioms(owlObjectPropertyExpression);
    	/**
    	readLock.lock();
        try {
            return delegate.getInverseFunctionalObjectPropertyAxioms(owlObjectPropertyExpression);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLSymmetricObjectPropertyAxiom> getSymmetricObjectPropertyAxioms(@Nonnull OWLObjectPropertyExpression owlObjectPropertyExpression) {
    	return delegate.getSymmetricObjectPropertyAxioms(owlObjectPropertyExpression);
    	/**
    	readLock.lock();
        try {
            return delegate.getSymmetricObjectPropertyAxioms(owlObjectPropertyExpression);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLAsymmetricObjectPropertyAxiom> getAsymmetricObjectPropertyAxioms(@Nonnull OWLObjectPropertyExpression owlObjectPropertyExpression) {
    	return delegate.getAsymmetricObjectPropertyAxioms(owlObjectPropertyExpression);
    	/**
    	readLock.lock();
        try {
            return delegate.getAsymmetricObjectPropertyAxioms(owlObjectPropertyExpression);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLReflexiveObjectPropertyAxiom> getReflexiveObjectPropertyAxioms(@Nonnull OWLObjectPropertyExpression owlObjectPropertyExpression) {
    	return delegate.getReflexiveObjectPropertyAxioms(owlObjectPropertyExpression);
    	/**
    	readLock.lock();
        try {
            return delegate.getReflexiveObjectPropertyAxioms(owlObjectPropertyExpression);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLIrreflexiveObjectPropertyAxiom> getIrreflexiveObjectPropertyAxioms(@Nonnull OWLObjectPropertyExpression owlObjectPropertyExpression) {
    	return delegate.getIrreflexiveObjectPropertyAxioms(owlObjectPropertyExpression);
    	/**
    	readLock.lock();
        try {
            return delegate.getIrreflexiveObjectPropertyAxioms(owlObjectPropertyExpression);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLTransitiveObjectPropertyAxiom> getTransitiveObjectPropertyAxioms(@Nonnull OWLObjectPropertyExpression owlObjectPropertyExpression) {
    	return delegate.getTransitiveObjectPropertyAxioms(owlObjectPropertyExpression);
    	/**
    	readLock.lock();
        try {
            return delegate.getTransitiveObjectPropertyAxioms(owlObjectPropertyExpression);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLSubDataPropertyOfAxiom> getDataSubPropertyAxiomsForSubProperty(@Nonnull OWLDataProperty owlDataProperty) {
    	return delegate.getDataSubPropertyAxiomsForSubProperty(owlDataProperty);
    	/**
    	readLock.lock();
        try {
            return delegate.getDataSubPropertyAxiomsForSubProperty(owlDataProperty);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLSubDataPropertyOfAxiom> getDataSubPropertyAxiomsForSuperProperty(@Nonnull OWLDataPropertyExpression owlDataPropertyExpression) {
    	return delegate.getDataSubPropertyAxiomsForSuperProperty(owlDataPropertyExpression);
    	/**
    	readLock.lock();
        try {
            return delegate.getDataSubPropertyAxiomsForSuperProperty(owlDataPropertyExpression);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLDataPropertyDomainAxiom> getDataPropertyDomainAxioms(@Nonnull OWLDataProperty owlDataProperty) {
    	return delegate.getDataPropertyDomainAxioms(owlDataProperty);
    	/**
    	readLock.lock();
        try {
            return delegate.getDataPropertyDomainAxioms(owlDataProperty);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLDataPropertyRangeAxiom> getDataPropertyRangeAxioms(@Nonnull OWLDataProperty owlDataProperty) {
    	return delegate.getDataPropertyRangeAxioms(owlDataProperty);
    	/**
    	readLock.lock();
        try {
            return delegate.getDataPropertyRangeAxioms(owlDataProperty);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLEquivalentDataPropertiesAxiom> getEquivalentDataPropertiesAxioms(@Nonnull OWLDataProperty owlDataProperty) {
    	return delegate.getEquivalentDataPropertiesAxioms(owlDataProperty);
    	/**
    	readLock.lock();
        try {
            return delegate.getEquivalentDataPropertiesAxioms(owlDataProperty);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLDisjointDataPropertiesAxiom> getDisjointDataPropertiesAxioms(@Nonnull OWLDataProperty owlDataProperty) {
    	return delegate.getDisjointDataPropertiesAxioms(owlDataProperty);
    	/**
    	readLock.lock();
        try {
            return delegate.getDisjointDataPropertiesAxioms(owlDataProperty);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLFunctionalDataPropertyAxiom> getFunctionalDataPropertyAxioms(@Nonnull OWLDataPropertyExpression owlDataPropertyExpression) {
    	return delegate.getFunctionalDataPropertyAxioms(owlDataPropertyExpression);
    	/**
    	readLock.lock();
        try {
            return delegate.getFunctionalDataPropertyAxioms(owlDataPropertyExpression);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLClassAssertionAxiom> getClassAssertionAxioms(@Nonnull OWLIndividual owlIndividual) {
    	return delegate.getClassAssertionAxioms(owlIndividual);
    	/**
    	readLock.lock();
        try {
            return delegate.getClassAssertionAxioms(owlIndividual);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLClassAssertionAxiom> getClassAssertionAxioms(@Nonnull OWLClassExpression owlClassExpression) {
    	return delegate.getClassAssertionAxioms(owlClassExpression);
    	/**
    	readLock.lock();
        try {
            return delegate.getClassAssertionAxioms(owlClassExpression);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLDataPropertyAssertionAxiom> getDataPropertyAssertionAxioms(@Nonnull OWLIndividual owlIndividual) {
    	return delegate.getDataPropertyAssertionAxioms(owlIndividual);
    	/**
    	readLock.lock();
        try {
            return delegate.getDataPropertyAssertionAxioms(owlIndividual);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLObjectPropertyAssertionAxiom> getObjectPropertyAssertionAxioms(@Nonnull OWLIndividual owlIndividual) {
    	return delegate.getObjectPropertyAssertionAxioms(owlIndividual);
    	/**
    	readLock.lock();
        try {
            return delegate.getObjectPropertyAssertionAxioms(owlIndividual);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLNegativeObjectPropertyAssertionAxiom> getNegativeObjectPropertyAssertionAxioms(@Nonnull OWLIndividual owlIndividual) {
    	return delegate.getNegativeObjectPropertyAssertionAxioms(owlIndividual);
    	/**
    	readLock.lock();
        try {
            return delegate.getNegativeObjectPropertyAssertionAxioms(owlIndividual);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLNegativeDataPropertyAssertionAxiom> getNegativeDataPropertyAssertionAxioms(@Nonnull OWLIndividual owlIndividual) {
    	return delegate.getNegativeDataPropertyAssertionAxioms(owlIndividual);
    	/**
    	readLock.lock();
        try {
            return delegate.getNegativeDataPropertyAssertionAxioms(owlIndividual);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLSameIndividualAxiom> getSameIndividualAxioms(@Nonnull OWLIndividual owlIndividual) {
    	return delegate.getSameIndividualAxioms(owlIndividual);
    	/**
    	readLock.lock();
        try {
            return delegate.getSameIndividualAxioms(owlIndividual);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLDifferentIndividualsAxiom> getDifferentIndividualAxioms(@Nonnull OWLIndividual owlIndividual) {
    	return delegate.getDifferentIndividualAxioms(owlIndividual);
    	/**
    	readLock.lock();
        try {
            return delegate.getDifferentIndividualAxioms(owlIndividual);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public Set<OWLDatatypeDefinitionAxiom> getDatatypeDefinitions(@Nonnull OWLDatatype owlDatatype) {
    	return delegate.getDatatypeDefinitions(owlDatatype);
    	/**
    	readLock.lock();
        try {
            return delegate.getDatatypeDefinitions(owlDatatype);
        } finally {
            readLock.unlock();
        }
        **/
    }

    @Override
    @Nonnull
    public ChangeApplied applyChange(@Nonnull OWLOntologyChange owlOntologyChange) {
        writeLock.lock();
        try {
            return getMutableOntology().applyChange(owlOntologyChange);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    @Nonnull
    public ChangeApplied applyChanges(
        @Nonnull List<? extends OWLOntologyChange> list) {
        writeLock.lock();
        try {
            return getMutableOntology().applyChanges(list);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    @Nonnull
    public ChangeApplied addAxiom(@Nonnull OWLAxiom owlAxiom) {
        writeLock.lock();
        try {
            return getMutableOntology().addAxiom(owlAxiom);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public ChangeApplied addAxioms(@Nonnull Set<? extends OWLAxiom> set) {
        writeLock.lock();
        try {
            return getMutableOntology().addAxioms(set);
        } finally {
            writeLock.unlock();
        }
    }

    private OWLMutableOntology getMutableOntology() {
        return (OWLMutableOntology) delegate;
    }
}
