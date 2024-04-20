export default function FieldSetComponnet({ label, children }) {
  return (
    <fieldset className="mt-5">
      {/* this is main form compnnet */}
      {label && (
        <legend className=" bg-primary_colour py-5  text-center text-xl text-white font-semibold w-full mb-2">
          {label}
        </legend>
      )}
      <div className=""> {children}</div>
    </fieldset>
  );
}
