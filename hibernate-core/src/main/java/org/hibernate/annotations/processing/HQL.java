/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.annotations.processing;

import org.hibernate.Incubating;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Identifies a method of an abstract class or interface as defining
 * the signature of a method which is used to execute the given
 * {@linkplain #value HQL query}, and is generated automatically by
 * the Hibernate Metamodel Generator.
 * <p>
 * For example:
 * <pre>
 * public interface Books {
 *     &#64;HQL("from Book where isbn = :isbn")
 *     Book findBookByIsbn(String isbn);
 *
 *     &#64;HQL("from Book where title like ?1 order by title offset ?3 fetch first ?2 rows only")
 *     List&lt;Book&gt; findBooksByTitleWithPagination(String title, int max, int start);
 *
 *     &#64;HQL("from Book where title like ?1")
 *     TypedQuery&lt;Book&gt; findBooksByTitle(String title);
 * }
 * </pre>
 * <p>
 * The Metamodel Generator automatically creates an "implementation"
 * of these methods in the static metamodel class {@code Books_}.
 * <pre>
 * Book book = Books_.findBookByIsbn(session, isbn);
 * List&lt;Book&gt; books = Books_.findBooksByTitleWithPagination(session, pattern, 10, 0);
 * </pre>
 * <p>
 * The return type of an annotated method must be:
 * <ul>
 * <li>an entity type,
 * <li>{@link java.util.List},
 * <li>{@link org.hibernate.query.Query},
 * <li>{@link jakarta.persistence.Query}, or
 * <li>{@link jakarta.persistence.TypedQuery}.
 * </ul>
 * <p>
 * The method parameters must match the parameters of the HQL query,
 * either by name or by position.
 *
 * @author Gavin King
 * @since 6.3
 */
@Target(METHOD)
@Retention(CLASS)
@Incubating
public @interface HQL {
	String value();
}
