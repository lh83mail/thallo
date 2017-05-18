// Generated from /home/lihong/Workspace/SourceCode/opensource/thallo/Study/src/main/resources/Filter.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FilterParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FilterVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FilterParser#r}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitR(FilterParser.RContext ctx);
	/**
	 * Visit a parse tree produced by {@link FilterParser#pair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPair(FilterParser.PairContext ctx);
	/**
	 * Visit a parse tree produced by {@link FilterParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter(FilterParser.FilterContext ctx);
	/**
	 * Visit a parse tree produced by {@link FilterParser#not}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(FilterParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by {@link FilterParser#op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp(FilterParser.OpContext ctx);
}